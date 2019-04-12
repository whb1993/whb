import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVueUser } from 'app/shared/model/vue-user.model';
import { VueUserService } from './vue-user.service';

@Component({
    selector: 'jhi-vue-user-update',
    templateUrl: './vue-user-update.component.html'
})
export class VueUserUpdateComponent implements OnInit {
    vueUser: IVueUser;
    isSaving: boolean;
    creatTime: string;
    updataTime: string;
    lockTime: string;

    constructor(protected vueUserService: VueUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vueUser }) => {
            this.vueUser = vueUser;
            this.creatTime = this.vueUser.creatTime != null ? this.vueUser.creatTime.format(DATE_TIME_FORMAT) : null;
            this.updataTime = this.vueUser.updataTime != null ? this.vueUser.updataTime.format(DATE_TIME_FORMAT) : null;
            this.lockTime = this.vueUser.lockTime != null ? this.vueUser.lockTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.vueUser.creatTime = this.creatTime != null ? moment(this.creatTime, DATE_TIME_FORMAT) : null;
        this.vueUser.updataTime = this.updataTime != null ? moment(this.updataTime, DATE_TIME_FORMAT) : null;
        this.vueUser.lockTime = this.lockTime != null ? moment(this.lockTime, DATE_TIME_FORMAT) : null;
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
