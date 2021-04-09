import { IJfWork } from '@/shared/model/jf-work.model';

export interface IJfWorkDetails {
  id?: number;
  rq?: Date | null;
  lb?: string | null;
  shl?: number | null;
  gq?: string | null;
  syl?: number | null;
  master?: IJfWork | null;
}

export class JfWorkDetails implements IJfWorkDetails {
  constructor(
    public id?: number,
    public rq?: Date | null,
    public lb?: string | null,
    public shl?: number | null,
    public gq?: string | null,
    public syl?: number | null,
    public master?: IJfWork | null
  ) {}
}
