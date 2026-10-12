# Variables note — which variables each worker reads and writes

| Worker (task type) | Service task | Reads | Writes |
|---|---|---|---|
| `check-outpass` | Check request | `regNo`, `outDate`, `returnDate` | `nights`, `valid`, `approved` (`true` only for a valid day-out) |
| `issue-outpass` | Issue out-pass | `regNo`, `outDate`, `studentName`, `parentPhone`, `approved`, `wardenRemarks` | `passStatus` (`ISSUED` / `REJECTED`), `passNumber` (`OP-<regNo>-<outDate>`, only when ISSUED) |

**Start variables (from POST /outpass):** studentName, regNo, hostelBlock, outDate, returnDate, parentPhone, purpose.

**Written by the Warden form (user task):** `approved` (checkbox) and `wardenRemarks` (text area). The form's `approved` overwrites the value set by `check-outpass`.

## How the gateway uses them
| Path | Condition | Result |
|---|---|---|
| Invalid dates | `=not(valid)` | Straight to *Issue out-pass* → `passStatus = REJECTED` |
| Day out | `=valid and nights = 0` | Auto-approved → `passStatus = ISSUED` |
| Overnight | `=valid and nights >= 1` | *Warden approval* user task → ISSUED if ticked, otherwise REJECTED |

`valid` is `false` when the return date is before the out date (or a date is not in `yyyy-MM-dd` format).
