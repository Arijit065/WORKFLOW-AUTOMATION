# Screenshots to add (take them from your own Camunda cluster)

Save the images in this folder using these names, then commit them.

| File name | What to capture |
|---|---|
| `01-modeler-bpmn.png` | Web Modeler showing the hostel-outpass process |
| `02-modeler-form.png` | Web Modeler showing the warden-approval form |
| `03-operate-auto-approved.png` | Operate: the day-out instance **Completed**, path + Variables panel (`nights=0`, `passStatus=ISSUED`) |
| `04-tasklist-warden-form.png` | Tasklist: the Warden approval task with the form open |
| `05-operate-warden-approved.png` | Operate: the overnight instance **Completed** after the warden approved (`remarks`, `passNumber`) |
| `06-operate-rejected.png` | Operate: the invalid-dates instance **Completed** with `passStatus=REJECTED` |
| `07-app-log.png` | Terminal showing the "SMS to parent ..." lines |
