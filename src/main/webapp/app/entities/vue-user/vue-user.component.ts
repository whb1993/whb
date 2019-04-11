import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVueUser } from 'app/shared/model/vue-user.model';
import { AccountService } from 'app/core';
import { VueUserService } from './vue-user.service';

@Component({
    selector: 'jhi-vue-user',
    templateUrl: './vue-user.component.html'
})
export class VueUserComponent implements OnInit, OnDestroy {
    vueUsers: IVueUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected vueUserService: VueUserService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.vueUserService
            .query()
            .pipe(
                filter((res: HttpResponse<IVueUser[]>) => res.ok),
                map((res: HttpResponse<IVueUser[]>) => res.body)
            )
            .subscribe(
                (res: IVueUser[]) => {
                    this.vueUsers = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVueUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVueUser) {
        return item.id;
    }

    registerChangeInVueUsers() {
        this.eventSubscriber = this.eventManager.subscribe('vueUserListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
