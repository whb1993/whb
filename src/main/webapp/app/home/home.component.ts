import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BestUser, IBestUser } from 'app/shared/model/best-user.model';
import { Observable } from 'rxjs/index';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { BestUserService } from 'app/entities/best-user/best-user.service';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    bestUser = new BestUser();
    isSaving: boolean;

    showBestUser = true;
    constructor(protected bestUserService: BestUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
    }

    previousState() {
        this.bestUser = new BestUser();
    }

    save() {
        this.isSaving = true;
        this.subscribeToSaveResponse(this.bestUserService.create(this.bestUser));
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBestUser>>) {
        result.subscribe((res: HttpResponse<IBestUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.showBestUser = false;
        // this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
