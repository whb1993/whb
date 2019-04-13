import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BestUser, IBestUser } from 'app/shared/model/best-user.model';
import { Observable } from 'rxjs/index';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { BestUserService } from 'app/entities/best-user/best-user.service';
import { JhiAlertService, JhiLanguageService } from 'ng-jhipster';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    // @ViewChild
    @ViewChild('editForm')
    editForm: NgForm;
    bestUser = new BestUser();
    isSaving: boolean;

    showBestUser = true;
    constructor(
        protected bestUserService: BestUserService,
        protected jhiAlertService: JhiAlertService,
        private languageService: JhiLanguageService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.languageService.changeLanguage('en');
    }

    previousState() {
        this.bestUser = new BestUser();
        this.editForm.onReset();
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
        // this.showBestUser = false;
        this.jhiAlertService.success('添加成功');
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
