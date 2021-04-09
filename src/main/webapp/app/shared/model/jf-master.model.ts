import { IBtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';

export interface IJfMaster {
  id?: number;
  zygs?: string | null;
  je?: number | null;
  she?: number | null;
  shhje?: number | null;
  jmje?: number | null;
  jmhje?: number | null;
  idHw?: number | null;
  htid?: number | null;
  mb?: string | null;
  zywtrid?: number | null;
  mark1?: string | null;
  mark2?: string | null;
  createby?: string | null;
  createbyid?: number | null;
  createon?: Date | null;
  createGsid?: string | null;
  createGsmch?: string | null;
  createBmid?: number | null;
  createBmm?: string | null;
  createGwid?: string | null;
  createGwm?: string | null;
  modifyby?: string | null;
  modifybyid?: number | null;
  modifyon?: Date | null;
  btjfjtjfmxes?: IBtJfJtJfmx[] | null;
}

export class JfMaster implements IJfMaster {
  constructor(
    public id?: number,
    public zygs?: string | null,
    public je?: number | null,
    public she?: number | null,
    public shhje?: number | null,
    public jmje?: number | null,
    public jmhje?: number | null,
    public idHw?: number | null,
    public htid?: number | null,
    public mb?: string | null,
    public zywtrid?: number | null,
    public mark1?: string | null,
    public mark2?: string | null,
    public createby?: string | null,
    public createbyid?: number | null,
    public createon?: Date | null,
    public createGsid?: string | null,
    public createGsmch?: string | null,
    public createBmid?: number | null,
    public createBmm?: string | null,
    public createGwid?: string | null,
    public createGwm?: string | null,
    public modifyby?: string | null,
    public modifybyid?: number | null,
    public modifyon?: Date | null,
    public btjfjtjfmxes?: IBtJfJtJfmx[] | null
  ) {}
}
