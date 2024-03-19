import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDeckComponent } from './view-deck.component';

describe('ViewDeckComponent', () => {
  let component: ViewDeckComponent;
  let fixture: ComponentFixture<ViewDeckComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewDeckComponent]
    });
    fixture = TestBed.createComponent(ViewDeckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
