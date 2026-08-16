# WORKFLOW-AUTOMATION
**Name:** Arijit Mandal

**Register Number:** RA2411056010253

## Scenarios

1. **Employee Leave Approval** – an employee applies for leave; the system checks leave balance, routes the request to the manager for approval, and sends the appropriate notification (approved, rejected, or insufficient balance).
2. **Online Purchase Order Processing** – a customer places an order; the system checks product availability, processes payment, and either ships the order or notifies the customer of an issue (out of stock or payment failure).
3. **IT Service Request** – an employee reports an IT problem; the help desk registers it, assigns it based on severity, and the technician resolves or escalates it before the employee is notified.

## Repository contents

```text
WORKFLOW-AUTOMATION/
├── README.md
├── Experiment-1(ARIJIT).pdf          # report explaining each scenario and its BPMN process
├── Diagrams/
│   ├── Scenario-1.png                # Employee Leave Approval
│   ├── Scenario 2.png                # Online Purchase Order Processing
│   └── Scenario 3.png                # IT Service Request
└── models/
    ├── scenario1-leave-approval.bpmn
    ├── scenario2-purchase-order.bpmn
    └── scenario3-it-service-request.bpmn
```

## How to view the models

Open any `.bpmn` file from the `models/` folder in [Camunda Modeler](https://camunda.com/download/modeler/) (File → Open File) to view and edit the diagram. The `Diagrams/` folder has ready-made image exports of each model, and the PDF report explains the logic behind every scenario.
