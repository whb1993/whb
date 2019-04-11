import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WhbSharedModule } from 'app/shared';
import {
    VueUserComponent,
    VueUserDetailComponent,
    VueUserUpdateComponent,
    VueUserDeletePopupComponent,
    VueUserDeleteDialogComponent,
    vueUserRoute,
    vueUserPopupRoute
} from './';

const ENTITY_STATES = [...vueUserRoute, ...vueUserPopupRoute];

@NgModule({
    imports: [WhbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VueUserComponent,
        VueUserDetailComponent,
        VueUserUpdateComponent,
        VueUserDeleteDialogComponent,
        VueUserDeletePopupComponent
    ],
    entryComponents: [VueUserComponent, VueUserUpdateComponent, VueUserDeleteDialogComponent, VueUserDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WhbVueUserModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
