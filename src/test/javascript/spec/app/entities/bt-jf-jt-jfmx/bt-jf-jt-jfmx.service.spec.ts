/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import BtJfJtJfmxService from '@/entities/bt-jf-jt-jfmx/bt-jf-jt-jfmx.service';
import { BtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('BtJfJtJfmx Service', () => {
    let service: BtJfJtJfmxService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BtJfJtJfmxService();
      currentDate = new Date();
      elemDefault = new BtJfJtJfmx(
        0,
        0,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            startd: dayjs(currentDate).format(DATE_FORMAT),
            endd: dayjs(currentDate).format(DATE_FORMAT),
            createon: dayjs(currentDate).format(DATE_FORMAT),
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a BtJfJtJfmx', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startd: dayjs(currentDate).format(DATE_FORMAT),
            endd: dayjs(currentDate).format(DATE_FORMAT),
            createon: dayjs(currentDate).format(DATE_FORMAT),
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startd: currentDate,
            endd: currentDate,
            createon: currentDate,
            modifyon: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a BtJfJtJfmx', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a BtJfJtJfmx', async () => {
        const returnedFromService = Object.assign(
          {
            zygs: 1,
            zyetrid: 1,
            flmch: 'BBBBBB',
            fl: 1,
            shl: 1,
            je: 1,
            shlv: 1,
            she: 1,
            shhje: 1,
            jmje: 1,
            startd: dayjs(currentDate).format(DATE_FORMAT),
            endd: dayjs(currentDate).format(DATE_FORMAT),
            mark1: 'BBBBBB',
            mark2: 'BBBBBB',
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsid: 'BBBBBB',
            createGsmch: 'BBBBBB',
            createBmid: 1,
            createBmm: 'BBBBBB',
            createGwid: 1,
            createGwm: 'BBBBBB',
            modifyby: 'BBBBBB',
            modifybyid: 1,
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startd: currentDate,
            endd: currentDate,
            createon: currentDate,
            modifyon: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a BtJfJtJfmx', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a BtJfJtJfmx', async () => {
        const patchObject = Object.assign(
          {
            zyetrid: 1,
            shlv: 1,
            jmje: 1,
            startd: dayjs(currentDate).format(DATE_FORMAT),
            mark2: 'BBBBBB',
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsmch: 'BBBBBB',
            createBmid: 1,
            createBmm: 'BBBBBB',
            modifybyid: 1,
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          new BtJfJtJfmx()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            startd: currentDate,
            endd: currentDate,
            createon: currentDate,
            modifyon: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a BtJfJtJfmx', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of BtJfJtJfmx', async () => {
        const returnedFromService = Object.assign(
          {
            zygs: 1,
            zyetrid: 1,
            flmch: 'BBBBBB',
            fl: 1,
            shl: 1,
            je: 1,
            shlv: 1,
            she: 1,
            shhje: 1,
            jmje: 1,
            startd: dayjs(currentDate).format(DATE_FORMAT),
            endd: dayjs(currentDate).format(DATE_FORMAT),
            mark1: 'BBBBBB',
            mark2: 'BBBBBB',
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsid: 'BBBBBB',
            createGsmch: 'BBBBBB',
            createBmid: 1,
            createBmm: 'BBBBBB',
            createGwid: 1,
            createGwm: 'BBBBBB',
            modifyby: 'BBBBBB',
            modifybyid: 1,
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startd: currentDate,
            endd: currentDate,
            createon: currentDate,
            modifyon: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of BtJfJtJfmx', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a BtJfJtJfmx', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a BtJfJtJfmx', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
