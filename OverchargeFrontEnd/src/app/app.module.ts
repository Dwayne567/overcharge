import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';

import { DeckListComponent } from './components/deck-list/deck-list.component';
import { CreateDeckComponent } from './components/create-deck/create-deck.component';
import { EditDeckComponent } from './components/edit-deck/edit-deck.component';
import { ViewDeckComponent } from './components/view-deck/view-deck.component';

@NgModule({
  declarations: [
    AppComponent,

    HeaderComponent,
    FooterComponent,
    
    DeckListComponent,
    CreateDeckComponent,
    EditDeckComponent,
    ViewDeckComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
