package edu.srmist.outpass.worker;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Two job workers. The "type" links each method to a service task in the BPMN.
 * The job completes automatically when the method returns; the returned Map
 * is written back to the process as variables.
 */
@Component
public class OutpassWorkers {

    private static final Logger log = LoggerFactory.getLogger(OutpassWorkers.class);

    /**
     * Service task "Check request" (type = check-outpass).
     * Reads : regNo, outDate, returnDate
     * Writes: nights, valid, approved (true only for a valid day-out)
     */
    @JobWorker(type = "check-outpass")
    public Map<String, Object> checkOutpass(@Variable String regNo,
                                            @Variable String outDate,
                                            @Variable String returnDate) {
        long nights = 0;
        boolean valid;
        try {
            LocalDate out = LocalDate.parse(outDate);
            LocalDate ret = LocalDate.parse(returnDate);
            nights = ChronoUnit.DAYS.between(out, ret);
            valid = !ret.isBefore(out);           // invalid if return date is before out date
        } catch (DateTimeParseException | NullPointerException e) {
            log.warn("Invalid date for {}: out={}, return={}", regNo, outDate, returnDate);
            valid = false;
        }
        boolean approved = valid && nights == 0;   // day-out is auto-approved

        log.info("Check request {}: nights={}, valid={}, autoApproved={}", regNo, nights, valid, approved);

        Map<String, Object> result = new HashMap<>();
        result.put("nights", nights);
        result.put("valid", valid);
        result.put("approved", approved);
        return result;
    }

    /**
     * Service task "Issue out-pass" (type = issue-outpass).
     * Reads : regNo, outDate, studentName, parentPhone, approved, wardenRemarks
     * Writes: passStatus (ISSUED / REJECTED), passNumber (OP-<regNo>-<outDate>, only when ISSUED)
     */
    @JobWorker(type = "issue-outpass")
    public Map<String, Object> issueOutpass(@Variable String regNo,
                                            @Variable String outDate,
                                            @Variable String studentName,
                                            @Variable String parentPhone,
                                            @Variable Boolean approved,
                                            @Variable String wardenRemarks) {
        boolean issued = Boolean.TRUE.equals(approved);
        String passStatus = issued ? "ISSUED" : "REJECTED";
        String passNumber = issued ? "OP-" + regNo + "-" + outDate : null;

        log.info("SMS to parent {} ({}): out-pass for {} is {}{}. Warden remarks: {}",
                parentPhone, studentName, regNo, passStatus,
                issued ? " (Pass No. " + passNumber + ")" : "",
                wardenRemarks == null ? "-" : wardenRemarks);

        Map<String, Object> result = new HashMap<>();
        result.put("passStatus", passStatus);
        result.put("passNumber", passNumber);
        return result;
    }
}
