/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { WhbTestModule } from '../../../test.module';
import { VueUserDeleteDialogComponent } from 'app/entities/vue-user/vue-user-delete-dialog.component';
import { VueUserService } from 'app/entities/vue-user/vue-user.service';

describe('Component Tests', () => {
    describe('VueUser Management Delete Component', () => {
        let comp: VueUserDeleteDialogComponent;
        let fixture: ComponentFixture<VueUserDeleteDialogComponent>;
        let service: VueUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [VueUserDeleteDialogComponent]
            })
                .overrideTemplate(VueUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VueUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VueUserService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
