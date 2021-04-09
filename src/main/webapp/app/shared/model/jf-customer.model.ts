export interface IJfCustomer {
  id?: number;
  name?: string | null;
}

export class JfCustomer implements IJfCustomer {
  constructor(public id?: number, public name?: string | null) {}
}
