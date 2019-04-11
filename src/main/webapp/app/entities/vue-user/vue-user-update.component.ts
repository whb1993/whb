import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IVueUser } from 'app/shared/model/vue-user.model';
import { VueUserService } from './vue-user.service';

@Component({
    selector: 'jhi-vue-user-update',
    templateUrl: './vue-user-update.component.html'
})
export class VueUserUpdateComponent implements OnInit {
    vueUser: IVueUser;
    isSaving: boolean;
    creatTimeDp: any;
    lockTimeDp: any;

    constructor(protected vueUserService: VueUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vueUser }) => {
            this.vueUser = vueUser;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vueUser.id !== undefined) {
            this.subscribeToSaveResponse(this.vueUserService.update(this.vueUser));
        } else {
            this.subscribeToSaveResponse(this.vueUserService.create(this.vueUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVueUser>>) {
        result.subscribe((res: HttpResponse<IVueUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
