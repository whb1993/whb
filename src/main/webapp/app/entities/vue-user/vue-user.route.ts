import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VueUser } from 'app/shared/model/vue-user.model';
import { VueUserService } from './vue-user.service';
import { VueUserComponent } from './vue-user.component';
import { VueUserDetailComponent } from './vue-user-detail.component';
import { VueUserUpdateComponent } from './vue-user-update.component';
import { VueUserDeletePopupComponent } from './vue-user-delete-dialog.component';
import { IVueUser } from 'app/shared/model/vue-user.model';

@Injectable({ providedIn: 'root' })
export class VueUserResolve implements Resolve<IVueUser> {
    constructor(private service: VueUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVueUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VueUser>) => response.ok),
                map((vueUser: HttpResponse<VueUser>) => vueUser.body)
            );
        }
        return of(new VueUser());
    }
}

export const vueUserRoute: Routes = [
    {
        path: '',
        component: VueUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.vueUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VueUserDetailComponent,
        resolve: {
            vueUser: VueUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.vueUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VueUserUpdateComponent,
        resolve: {
            vueUser: VueUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.vueUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VueUserUpdateComponent,
        resolve: {
            vueUser: VueUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.vueUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vueUserPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VueUserDeletePopupComponent,
        resolve: {
            vueUser: VueUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'whbApp.vueUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
