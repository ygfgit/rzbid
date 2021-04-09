/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import JfCargoService from '@/entities/jf-cargo/jf-cargo.service';
import { JfCargo } from '@/shared/model/jf-cargo.model';

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
  describe('JfCargo Service', () => {
    let service: JfCargoService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new JfCargoService();
      currentDate = new Date();
      elemDefault = new JfCargo(0, currentDate, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            rq: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a JfCargo', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            rq: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            rq: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a JfCargo', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a JfCargo', async () => {
        const returnedFromService = Object.assign(
          {
            rq: dayjs(currentDate).format(DATE_FORMAT),
            zygsdm: 'BBBBBB',
            hth: 'BBBBBB',
            zywtrid: 1,
            zywtr: 'BBBBBB',
            zhwchm: 'BBBBBB',
            hwmch: 'BBBBBB',
            gq: 'BBBBBB',
            shl: 1,
            syl: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            rq: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a JfCargo', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a JfCargo', async () => {
        const patchObject = Object.assign(
          {
            rq: dayjs(currentDate).format(DATE_FORMAT),
            zygsdm: 'BBBBBB',
            zywtrid: 1,
            hwmch: 'BBBBBB',
            gq: 'BBBBBB',
          },
          new JfCargo()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            rq: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a JfCargo', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of JfCargo', async () => {
        const returnedFromService = Object.assign(
          {
            rq: dayjs(currentDate).format(DATE_FORMAT),
            zygsdm: 'BBBBBB',
            hth: 'BBBBBB',
            zywtrid: 1,
            zywtr: 'BBBBBB',
            zhwchm: 'BBBBBB',
            hwmch: 'BBBBBB',
            gq: 'BBBBBB',
            shl: 1,
            syl: 1,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            rq: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of JfCargo', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a JfCargo', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a JfCargo', async () => {
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
