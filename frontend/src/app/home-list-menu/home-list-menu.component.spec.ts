import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeListMenuComponent } from './home-list-menu.component';

describe('HomeListMenuComponent', () => {
  let component: HomeListMenuComponent;
  let fixture: ComponentFixture<HomeListMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeListMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeListMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
