import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVueUser } from 'app/shared/model/vue-user.model';

type EntityResponseType = HttpResponse<IVueUser>;
type EntityArrayResponseType = HttpResponse<IVueUser[]>;

@Injectable({ providedIn: 'root' })
export class VueUserService {
    public resourceUrl = SERVER_API_URL + 'api/vue-users';

    constructor(protected http: HttpClient) {}

    create(vueUser: IVueUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vueUser);
        return this.http
            .post<IVueUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vueUser: IVueUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vueUser);
        return this.http
            .put<IVueUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVueUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVueUser[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vueUser: IVueUser): IVueUser {
        const copy: IVueUser = Object.assign({}, vueUser, {
            creatTime: vueUser.creatTime != null && vueUser.creatTime.isValid() ? vueUser.creatTime.toJSON() : null,
            updataTime: vueUser.updataTime != null && vueUser.updataTime.isValid() ? vueUser.updataTime.toJSON() : null,
            lockTime: vueUser.lockTime != null && vueUser.lockTime.isValid() ? vueUser.lockTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creatTime = res.body.creatTime != null ? moment(res.body.creatTime) : null;
            res.body.updataTime = res.body.updataTime != null ? moment(res.body.updataTime) : null;
            res.body.lockTime = res.body.lockTime != null ? moment(res.body.lockTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((vueUser: IVueUser) => {
                vueUser.creatTime = vueUser.creatTime != null ? moment(vueUser.creatTime) : null;
                vueUser.updataTime = vueUser.updataTime != null ? moment(vueUser.updataTime) : null;
                vueUser.lockTime = vueUser.lockTime != null ? moment(vueUser.lockTime) : null;
            });
        }
        return res;
    }
}
