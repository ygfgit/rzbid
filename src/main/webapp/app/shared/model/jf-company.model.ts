export interface IJfCompany {
  id?: number;
  name?: string | null;
}

export class JfCompany implements IJfCompany {
  constructor(public id?: number, public name?: string | null) {}
}
