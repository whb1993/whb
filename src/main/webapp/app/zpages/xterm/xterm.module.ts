import { NgModule } from '@angular/core';
import { XtermComponent } from 'app/zpages/xterm/xterm.component';
import { RouterModule } from '@angular/router';
import { WhbSharedModule } from 'app/shared';
import { xtermRoute } from 'app/zpages/xterm/xterm.route';

@NgModule({
    declarations: [XtermComponent],
    imports: [WhbSharedModule, RouterModule.forChild(xtermRoute)]
})
export class XtermModule {}
