/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WhbTestModule } from '../../../test.module';
import { VueUserUpdateComponent } from 'app/entities/vue-user/vue-user-update.component';
import { VueUserService } from 'app/entities/vue-user/vue-user.service';
import { VueUser } from 'app/shared/model/vue-user.model';

describe('Component Tests', () => {
    describe('VueUser Management Update Component', () => {
        let comp: VueUserUpdateComponent;
        let fixture: ComponentFixture<VueUserUpdateComponent>;
        let service: VueUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [VueUserUpdateComponent]
            })
                .overrideTemplate(VueUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VueUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VueUserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new VueUser(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vueUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new VueUser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vueUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
