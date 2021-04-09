import { IJfWork } from '@/shared/model/jf-work.model';

export interface IJfTarget {
  id?: number;
  flzl?: number | null;
  master?: IJfWork | null;
}

export class JfTarget implements IJfTarget {
  constructor(public id?: number, public flzl?: number | null, public master?: IJfWork | null) {}
}
