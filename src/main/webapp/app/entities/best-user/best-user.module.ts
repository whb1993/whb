import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WhbSharedModule } from 'app/shared';
import {
    BestUserComponent,
    BestUserDetailComponent,
    BestUserUpdateComponent,
    BestUserDeletePopupComponent,
    BestUserDeleteDialogComponent,
    bestUserRoute,
    bestUserPopupRoute
} from './';

const ENTITY_STATES = [...bestUserRoute, ...bestUserPopupRoute];

@NgModule({
    imports: [WhbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BestUserComponent,
        BestUserDetailComponent,
        BestUserUpdateComponent,
        BestUserDeleteDialogComponent,
        BestUserDeletePopupComponent
    ],
    entryComponents: [BestUserComponent, BestUserUpdateComponent, BestUserDeleteDialogComponent, BestUserDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WhbBestUserModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
