import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { WhbSharedLibsModule, WhbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';
import { NgZorroAntdModule } from 'ng-zorro-antd';

@NgModule({
    imports: [WhbSharedLibsModule, WhbSharedCommonModule, NgZorroAntdModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [WhbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective, NgZorroAntdModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WhbSharedModule {
    static forRoot() {
        return {
            ngModule: WhbSharedModule
        };
    }
}
