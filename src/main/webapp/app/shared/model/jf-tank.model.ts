import { IJfWork } from '@/shared/model/jf-work.model';

export interface IJfTank {
  id?: number;
  zone?: string | null;
  code?: string | null;
  master?: IJfWork | null;
}

export class JfTank implements IJfTank {
  constructor(public id?: number, public zone?: string | null, public code?: string | null, public master?: IJfWork | null) {}
}
