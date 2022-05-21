export class PqrsStatus {
  private readonly statuses = [
    {
      status: "PENDING",
      hierarchy: 0,
    },
    {
      status: "REJECTED",
      hierarchy: 0,
    },
    {
      status: "APPROVED",
      hierarchy: 1,
    },
    {
      status: "IN_PROGRESS",
      hierarchy: 2,
    },
    {
      status: "CLOSED",
      hierarchy: 3,
    },
  ];

  constructor(private status: string) {}

  next() {
    return (
      this.statuses.find(
        (status) =>
          this.getHierarchy(this.status).hierarchy + 1 === status.hierarchy
      )?.status ?? this.status
    );
  }

  private getHierarchy(statusName: string) {
    return this.statuses.filter((status) => status.status === statusName)[0];
  }
}
