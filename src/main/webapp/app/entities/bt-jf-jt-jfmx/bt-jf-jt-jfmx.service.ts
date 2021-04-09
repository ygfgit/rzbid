import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IBtJfJtJfmx } from '@/shared/model/bt-jf-jt-jfmx.model';

const baseApiUrl = 'api/bt-jf-jt-jfmxes';

export default class BtJfJtJfmxService {
  public find(id: number): Promise<IBtJfJtJfmx> {
    return new Promise<IBtJfJtJfmx>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IBtJfJtJfmx): Promise<IBtJfJtJfmx> {
    return new Promise<IBtJfJtJfmx>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IBtJfJtJfmx): Promise<IBtJfJtJfmx> {
    return new Promise<IBtJfJtJfmx>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IBtJfJtJfmx): Promise<IBtJfJtJfmx> {
    return new Promise<IBtJfJtJfmx>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
