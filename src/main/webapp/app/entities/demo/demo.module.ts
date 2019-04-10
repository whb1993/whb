import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WhbSharedModule } from 'app/shared';
import {
    DemoComponent,
    DemoDetailComponent,
    DemoUpdateComponent,
    DemoDeletePopupComponent,
    DemoDeleteDialogComponent,
    demoRoute,
    demoPopupRoute
} from './';

const ENTITY_STATES = [...demoRoute, ...demoPopupRoute];

@NgModule({
    imports: [WhbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DemoComponent, DemoDetailComponent, DemoUpdateComponent, DemoDeleteDialogComponent, DemoDeletePopupComponent],
    entryComponents: [DemoComponent, DemoUpdateComponent, DemoDeleteDialogComponent, DemoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WhbDemoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
