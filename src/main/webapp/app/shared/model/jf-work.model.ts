import { IJfCargo } from '@/shared/model/jf-cargo.model';
import { IJfTarget } from '@/shared/model/jf-target.model';
import { IJfTank } from '@/shared/model/jf-tank.model';
import { IJfWorkDetails } from '@/shared/model/jf-work-details.model';

export interface IJfWork {
  id?: number;
  hwmch?: string | null;
  jhsl?: number | null;
  bz?: string | null;
  cargos?: IJfCargo[] | null;
  tagets?: IJfTarget[] | null;
  tanks?: IJfTank[] | null;
  details?: IJfWorkDetails[] | null;
}

export class JfWork implements IJfWork {
  constructor(
    public id?: number,
    public hwmch?: string | null,
    public jhsl?: number | null,
    public bz?: string | null,
    public cargos?: IJfCargo[] | null,
    public tagets?: IJfTarget[] | null,
    public tanks?: IJfTank[] | null,
    public details?: IJfWorkDetails[] | null
  ) {}
}
