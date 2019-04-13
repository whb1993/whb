import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IBestUser } from 'app/shared/model/best-user.model';
import { BestUserService } from './best-user.service';

@Component({
    selector: 'jhi-best-user-update',
    templateUrl: './best-user-update.component.html'
})
export class BestUserUpdateComponent implements OnInit {
    bestUser: IBestUser;
    isSaving: boolean;

    constructor(protected bestUserService: BestUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bestUser }) => {
            this.bestUser = bestUser;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bestUser.id !== undefined) {
            this.subscribeToSaveResponse(this.bestUserService.update(this.bestUser));
        } else {
            this.subscribeToSaveResponse(this.bestUserService.create(this.bestUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBestUser>>) {
        result.subscribe((res: HttpResponse<IBestUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
