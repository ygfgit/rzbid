/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import JfMasterService from '@/entities/jf-master/jf-master.service';
import { JfMaster } from '@/shared/model/jf-master.model';

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
  describe('JfMaster Service', () => {
    let service: JfMasterService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new JfMasterService();
      currentDate = new Date();
      elemDefault = new JfMaster(
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a JfMaster', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
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

      it('should not create a JfMaster', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a JfMaster', async () => {
        const returnedFromService = Object.assign(
          {
            zygs: 'BBBBBB',
            je: 1,
            she: 1,
            shhje: 1,
            jmje: 1,
            jmhje: 1,
            idHw: 1,
            htid: 1,
            mb: 'BBBBBB',
            zywtrid: 1,
            mark1: 'BBBBBB',
            mark2: 'BBBBBB',
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsid: 'BBBBBB',
            createGsmch: 'BBBBBB',
            createBmid: 1,
            createBmm: 'BBBBBB',
            createGwid: 'BBBBBB',
            createGwm: 'BBBBBB',
            modifyby: 'BBBBBB',
            modifybyid: 1,
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should not update a JfMaster', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a JfMaster', async () => {
        const patchObject = Object.assign(
          {
            zygs: 'BBBBBB',
            she: 1,
            shhje: 1,
            jmje: 1,
            mb: 'BBBBBB',
            zywtrid: 1,
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsid: 'BBBBBB',
            createGsmch: 'BBBBBB',
            createGwid: 'BBBBBB',
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          new JfMaster()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
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

      it('should not partial update a JfMaster', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of JfMaster', async () => {
        const returnedFromService = Object.assign(
          {
            zygs: 'BBBBBB',
            je: 1,
            she: 1,
            shhje: 1,
            jmje: 1,
            jmhje: 1,
            idHw: 1,
            htid: 1,
            mb: 'BBBBBB',
            zywtrid: 1,
            mark1: 'BBBBBB',
            mark2: 'BBBBBB',
            createby: 'BBBBBB',
            createbyid: 1,
            createon: dayjs(currentDate).format(DATE_FORMAT),
            createGsid: 'BBBBBB',
            createGsmch: 'BBBBBB',
            createBmid: 1,
            createBmm: 'BBBBBB',
            createGwid: 'BBBBBB',
            createGwm: 'BBBBBB',
            modifyby: 'BBBBBB',
            modifybyid: 1,
            modifyon: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
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

      it('should not return a list of JfMaster', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a JfMaster', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a JfMaster', async () => {
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
