import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forChild([
            {
                path: 'xterm',
                loadChildren: './xterm/xterm.module#XtermModule'
            }
        ])
    ]
})
export class PagesModule {}
