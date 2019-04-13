import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBestUser } from 'app/shared/model/best-user.model';

type EntityResponseType = HttpResponse<IBestUser>;
type EntityArrayResponseType = HttpResponse<IBestUser[]>;

@Injectable({ providedIn: 'root' })
export class BestUserService {
    public resourceUrl = SERVER_API_URL + 'api/best-users';

    constructor(protected http: HttpClient) {}

    create(bestUser: IBestUser): Observable<EntityResponseType> {
        return this.http.post<IBestUser>(this.resourceUrl, bestUser, { observe: 'response' });
    }

    update(bestUser: IBestUser): Observable<EntityResponseType> {
        return this.http.put<IBestUser>(this.resourceUrl, bestUser, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBestUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBestUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
