import { IJfMaster } from '@/shared/model/jf-master.model';

export interface IBtJfJtJfmx {
  id?: number;
  zygs?: number | null;
  zyetrid?: number | null;
  flmch?: string | null;
  fl?: number | null;
  shl?: number | null;
  je?: number | null;
  shlv?: number | null;
  she?: number | null;
  shhje?: number | null;
  jmje?: number | null;
  startd?: Date | null;
  endd?: Date | null;
  mark1?: string | null;
  mark2?: string | null;
  createby?: string | null;
  createbyid?: number | null;
  createon?: Date | null;
  createGsid?: string | null;
  createGsmch?: string | null;
  createBmid?: number | null;
  createBmm?: string | null;
  createGwid?: number | null;
  createGwm?: string | null;
  modifyby?: string | null;
  modifybyid?: number | null;
  modifyon?: Date | null;
  master?: IJfMaster | null;
}

export class BtJfJtJfmx implements IBtJfJtJfmx {
  constructor(
    public id?: number,
    public zygs?: number | null,
    public zyetrid?: number | null,
    public flmch?: string | null,
    public fl?: number | null,
    public shl?: number | null,
    public je?: number | null,
    public shlv?: number | null,
    public she?: number | null,
    public shhje?: number | null,
    public jmje?: number | null,
    public startd?: Date | null,
    public endd?: Date | null,
    public mark1?: string | null,
    public mark2?: string | null,
    public createby?: string | null,
    public createbyid?: number | null,
    public createon?: Date | null,
    public createGsid?: string | null,
    public createGsmch?: string | null,
    public createBmid?: number | null,
    public createBmm?: string | null,
    public createGwid?: number | null,
    public createGwm?: string | null,
    public modifyby?: string | null,
    public modifybyid?: number | null,
    public modifyon?: Date | null,
    public master?: IJfMaster | null
  ) {}
}
