import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'demo',
                loadChildren: './demo/demo.module#WhbDemoModule'
            },
            {
                path: 'vue-user',
                loadChildren: './vue-user/vue-user.module#WhbVueUserModule'
            },
            {
                path: 'vue-user',
                loadChildren: './vue-user/vue-user.module#WhbVueUserModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WhbEntityModule {}
