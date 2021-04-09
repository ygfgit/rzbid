import { IJfWork } from '@/shared/model/jf-work.model';

export interface IJfCargo {
  id?: number;
  rq?: Date | null;
  zygsdm?: string | null;
  hth?: string | null;
  zywtrid?: number | null;
  zywtr?: string | null;
  zhwchm?: string | null;
  hwmch?: string | null;
  gq?: string | null;
  shl?: number | null;
  syl?: number | null;
  master?: IJfWork | null;
}

export class JfCargo implements IJfCargo {
  constructor(
    public id?: number,
    public rq?: Date | null,
    public zygsdm?: string | null,
    public hth?: string | null,
    public zywtrid?: number | null,
    public zywtr?: string | null,
    public zhwchm?: string | null,
    public hwmch?: string | null,
    public gq?: string | null,
    public shl?: number | null,
    public syl?: number | null,
    public master?: IJfWork | null
  ) {}
}
