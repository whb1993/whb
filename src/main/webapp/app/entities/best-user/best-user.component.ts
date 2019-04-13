import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBestUser } from 'app/shared/model/best-user.model';
import { AccountService } from 'app/core';
import { BestUserService } from './best-user.service';

@Component({
    selector: 'jhi-best-user',
    templateUrl: './best-user.component.html'
})
export class BestUserComponent implements OnInit, OnDestroy {
    bestUsers: IBestUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected bestUserService: BestUserService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.bestUserService
            .query()
            .pipe(
                filter((res: HttpResponse<IBestUser[]>) => res.ok),
                map((res: HttpResponse<IBestUser[]>) => res.body)
            )
            .subscribe(
                (res: IBestUser[]) => {
                    this.bestUsers = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBestUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBestUser) {
        return item.id;
    }

    registerChangeInBestUsers() {
        this.eventSubscriber = this.eventManager.subscribe('bestUserListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
