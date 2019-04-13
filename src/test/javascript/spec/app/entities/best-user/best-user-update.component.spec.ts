/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WhbTestModule } from '../../../test.module';
import { BestUserUpdateComponent } from 'app/entities/best-user/best-user-update.component';
import { BestUserService } from 'app/entities/best-user/best-user.service';
import { BestUser } from 'app/shared/model/best-user.model';

describe('Component Tests', () => {
    describe('BestUser Management Update Component', () => {
        let comp: BestUserUpdateComponent;
        let fixture: ComponentFixture<BestUserUpdateComponent>;
        let service: BestUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [BestUserUpdateComponent]
            })
                .overrideTemplate(BestUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BestUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BestUserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new BestUser(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bestUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new BestUser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bestUser = entity;
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
