package edu.srmist.outpass.api;

/**
 * JSON body of POST /outpass. Every field becomes a process variable.
 * Dates use the format yyyy-MM-dd (e.g. 2026-10-20).
 */
public record OutpassRequest(
        String studentName,
        String regNo,
        String hostelBlock,
        String outDate,
        String returnDate,
        String parentPhone,
        String purpose) {
}
