import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BestUser } from 'app/shared/model/best-user.model';
import { BestUserService } from './best-user.service';
import { BestUserComponent } from './best-user.component';
import { BestUserDetailComponent } from './best-user-detail.component';
import { BestUserUpdateComponent } from './best-user-update.component';
import { BestUserDeletePopupComponent } from './best-user-delete-dialog.component';
import { IBestUser } from 'app/shared/model/best-user.model';

@Injectable({ providedIn: 'root' })
export class BestUserResolve implements Resolve<IBestUser> {
    constructor(private service: BestUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBestUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BestUser>) => response.ok),
                map((bestUser: HttpResponse<BestUser>) => bestUser.body)
            );
        }
        return of(new BestUser());
    }
}

export const bestUserRoute: Routes = [
    {
        path: '',
        component: BestUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.bestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: BestUserDetailComponent,
        resolve: {
            bestUser: BestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.bestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: BestUserUpdateComponent,
        resolve: {
            bestUser: BestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.bestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: BestUserUpdateComponent,
        resolve: {
            bestUser: BestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.bestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bestUserPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: BestUserDeletePopupComponent,
        resolve: {
            bestUser: BestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.bestUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
