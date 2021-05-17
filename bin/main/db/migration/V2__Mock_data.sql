-- Alpha Capitalis
insert into company (short_name, full_name, oib, mbs, email, address)
values ('Alpha Capitalis', 'ALPHA CAPITALIS d.o.o.', '40742241290', '02930854', 'info@alphacapitalis.com',
        'Ulica grada Vukovara 284, Zagreb');

-- Alpha Capitalis admin
insert into user_main (email, password, enabled, phone, first_name, last_name, position, address, company_id,
                       active_locale, designated_user, role)
values ('admin@mail.com', '$2a$13$6OgcSi13l9U6bi/3kUf4HuVofvaWFOHVC2tBFLgis2JTqGMs2Xrni', 'true', '0112345678',
        'Alpha Capitalis', 'Admin', 'Admin', 'Ulica grada Vukovara 284, Zagreb', 1, 'HR', 'true', 'A');

-- Končar d.o.o. - testna tvrtka
insert into company (short_name, full_name, oib, mbs, email, address)
values ('Končar', 'KONČAR - Elektroindustrija d.d.', '45050126417', '03282635', 'info@koncar.hr',
        'Fallerovo šetalište 22, 10000 Zagreb, Hrvatska');

-- User of 'Končar d.o.o.'
insert into user_main (email, password, enabled, phone, first_name, last_name, position, address, company_id,
                       active_locale, designated_user, role)
values ('korisnik@mail.com', '$2a$13$6OgcSi13l9U6bi/3kUf4HuVofvaWFOHVC2tBFLgis2JTqGMs2Xrni', 'true', '0991234567',
        'Končar', 'Korisnik', 'Referent', 'Čućerje 10, 10000 Zagreb', 2, 'HR', 'true', 'U');

insert into positions (company_id, sheet_type, position_properties)
values (1, 'balanceSheet', '[
    {
        "name": "Active",
        "positionProperties": [
            {
                "name": "A)  POTRAŽIVANJA ZA UPISANI A NEUPLAĆENI KAPITAL",
                "positionProperties": [
                    {
                        "name": "A)  POTRAŽIVANJA ZA UPISANI A NEUPLAĆENI KAPITAL",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [
                                    {
                                        "name": "2000 - neki naziv",
                                        "positionProperties": [],
                                        "konto": 2000
                                    }
                                ],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "B)  DUGOTRAJNA IMOVINA (AOP 003+010+020+031+036)",
                "positionProperties": [
                    {
                        "name": "I. NEMATERIJALNA IMOVINA (AOP 004 do 009)",
                        "positionProperties": [
                            {
                                "name": "1. Izdaci za razvoj",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Koncesije, patenti, licencije, robne i uslužne marke, softver i ostala prava",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Goodwill",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Predujmovi za nabavu nematerijalne imovine",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Nematerijalna imovina u pripremi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Ostala nematerijalna imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. MATERIJALNA IMOVINA (AOP 011 do 019)",
                        "positionProperties": [
                            {
                                "name": "1. Zemljište",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Građevinski objekti",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Postrojenja i oprema ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Alati, pogonski inventar i transportna imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Biološka imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Predujmovi za materijalnu imovinu",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Materijalna imovina u pripremi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Ostala materijalna imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ulaganje u nekretnine",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. DUGOTRAJNA FINANCIJSKA IMOVINA (AOP 021 do 030)",
                        "positionProperties": [
                            {
                                "name": "1. Ulaganja u udjele (dionice) poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Ulaganja u ostale vrijednosne papire poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Dani zajmovi, depoziti i slično poduzetnicima unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4.Ulaganja u udjele (dionice) društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ulaganja u ostale vrijednosne papire društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dani zajmovi, depoziti i slično društvima povezanim sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Ulaganja u vrijednosne papire",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Dani zajmovi, depoziti i slično",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ostala ulaganja koja se obračunavaju metodom udjela",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10.  Ostala dugotrajna financijska imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. POTRAŽIVANJA (AOP 032 do 035)",
                        "positionProperties": [
                            {
                                "name": "1. Potraživanja od poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Potraživanja od društava povezanih sudjelujućim interesom ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Potraživanja od kupaca ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Ostala potraživanja",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "V. ODGOĐENA POREZNA IMOVINA",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "C)  KRATKOTRAJNA IMOVINA (AOP 038+046+053+063)",
                "positionProperties": [
                    {
                        "name": "I. ZALIHE (AOP 039 do 045)",
                        "positionProperties": [
                            {
                                "name": "1. Sirovine i materijal",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Proizvodnja u tijeku",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Gotovi proizvodi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Trgovačka roba",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Predujmovi za zalihe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dugotrajna imovina namijenjena prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Biološka imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. POTRAŽIVANJA (AOP 047 do 052)",
                        "positionProperties": [
                            {
                                "name": "1. Potraživanja od poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Potraživanja od društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Potraživanja od kupaca K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Potraživanja od zaposlenika i članova poduzetnika",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Potraživanja od države i drugih institucija",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dugotrajna imovina namijenjena prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Ostala potraživanja K",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. KRATKOTRAJNA FINANCIJSKA IMOVINA (AOP 054 do 062)",
                        "positionProperties": [
                            {
                                "name": "1. Ulaganja u udjele (dionice) poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Ulaganja u ostale vrijednosne papire poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Dani zajmovi, depoziti i slično poduzetnicima unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Ulaganja u udjele (dionice) društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ulaganja u ostale vrijednosne papire društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dani zajmovi, depoziti i slično društvima povezanim sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Ulaganja u vrijednosne papire K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Dani zajmovi, depoziti i slično K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ostala financijska imovina K",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. NOVAC U BANCI I BLAGAJNI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "D)  PLAĆENI TROŠKOVI BUDUĆEG RAZDOBLJA I OBRAČUNATI PRIHODI",
                "positionProperties": [
                    {
                        "name": "D)  PLAĆENI TROŠKOVI BUDUĆEG RAZDOBLJA I OBRAČUNATI PRIHODI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "E)  UKUPNO AKTIVA (AOP 001+002+037+064)",
                "positionProperties": [
                    {
                        "name": "E)  UKUPNO AKTIVA (AOP 001+002+037+064)",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "F)  IZVANBILANČNI ZAPISI",
                "positionProperties": [
                    {
                        "name": "F)  IZVANBILANČNI ZAPISI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            }
        ]
    },
    {
        "name": "Pasive",
        "positionProperties": [
            {
                "name": "A)  KAPITAL I REZERVE (AOP 068 do 070+076+077+081+084+087)",
                "positionProperties": [
                    {
                        "name": "I. TEMELJNI (UPISANI) KAPITAL",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. KAPITALNE REZERVE",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. REZERVE IZ DOBITI (AOP 071+072-073+074+075)",
                        "positionProperties": [
                            {
                                "name": "1. Zakonske rezerve",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Rezerve za vlastite dionice",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Vlastite dionice i udjeli (odbitna stavka)",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Statutarne rezerve",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ostale rezerve",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. REVALORIZACIJSKE REZERVE",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "V. REZERVE FER VRIJEDNOSTI (AOP 078 do 080)",
                        "positionProperties": [
                            {
                                "name": "1. Fer vrijednost financijske imovine raspoložive za prodaju",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Učinkoviti dio zaštite novčanih tokova",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Učinkoviti dio zaštite neto ulaganja u inozemstvu",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VI. ZADRŽANA DOBIT ILI PRENESENI GUBITAK (AOP 082-083)",
                        "positionProperties": [
                            {
                                "name": "1. Zadržana dobit",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Preneseni gubitak",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VII. DOBIT ILI GUBITAK POSLOVNE GODINE (AOP 085-086)",
                        "positionProperties": [
                            {
                                "name": "1. Dobit poslovne godine",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Gubitak poslovne godine",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VIII. MANJINSKI (NEKONTROLIRAJUĆI) INTERES",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "B)  REZERVIRANJA (AOP 089 do 094)",
                "positionProperties": [
                    {
                        "name": "REZERVIRANJA",
                        "positionProperties": [
                            {
                                "name": "1. Rezerviranja za mirovine, otpremnine i slične obvez",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Rezerviranja za porezne obveze",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Rezerviranja za započete sudske sporove",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Rezerviranja za troškove obnavljanja prirodnih bogatstava",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Rezerviranja za troškove u jamstvenim rokovima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Druga rezerviranja",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "C)  DUGOROČNE OBVEZE (AOP 096 do 106)",
                "positionProperties": [
                    {
                        "name": "DUGOROČNE OBVEZE",
                        "positionProperties": [
                            {
                                "name": "1. Obveze prema poduzetnicima unutar grupe ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Obveze za zajmove, depozite i slično poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Obveze prema društvima povezanim sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Obveze za zajmove, depozite i slično društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Obveze za zajmove, depozite i slično",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Obveze prema bankama i drugim financijskim institucijama",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Obveze za predujmove",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Obveze prema dobavljačima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Obveze po vrijednosnim papirima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10. Ostale dugoročne obveze",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "11. Odgođena porezna obveza",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "D)  KRATKOROČNE OBVEZE (AOP 108 do 121)",
                "positionProperties": [
                    {
                        "name": "KRATKOROČNE OBVEZE",
                        "positionProperties": [
                            {
                                "name": "1. Obveze prema poduzetnicima unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Obveze za zajmove, depozite i slično poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Obveze prema društvima povezanim sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Obveze za zajmove, depozite i slično društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Obveze za zajmove, depozite i slično K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Obveze prema bankama i drugim financijskim institucijama K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Obveze za predujmove K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Obveze prema dobavljačima K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Obveze po vrijednosnim papirima K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10. Obveze prema zaposlenicima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "11. Obveze  za poreze, doprinose i sličana davanja",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "12. Obveze s osnove udjela u rezultatu",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "13. Obveze po osnovi dugotrajne imovine namijenjene prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "14. Ostale kratkoročne obveze",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "E) ODGOĐENO PLAĆANJE TROŠKOVA I PRIHOD BUDUĆEGA RAZDOBLJA",
                "positionProperties": [
                    {
                        "name": "ODGOĐENO PLAĆANJE TROŠKOVA I PRIHOD BUDUĆEGA RAZDOBLJA",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "F) UKUPNO – PASIVA (AOP 067+088+095+107+122)",
                "positionProperties": [
                    {
                        "name": "UKUPNO – PASIVA (AOP 067+088+095+107+122)",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "G)  IZVANBILANČNI ZAPISI",
                "positionProperties": [
                    {
                        "name": "IZVANBILANČNI ZAPISI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            }
        ]
    }
]');

insert into positions (company_id, sheet_type, position_properties)
values(1, 'profitLoss', '[
   {
      "name":"I. POSLOVNI PRIHODI (AOP 126 do 130)",
      "positionProperties":[
         {
            "name":"1. Prihodi od prodaje s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Prihodi od prodaje (izvan grupe)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Prihodi na temelju upotrebe vlastitih proizvoda, robe i usluga",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Ostali poslovni prihodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Ostali poslovni prihodi (izvan grupe)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"II. POSLOVNI RASHODI (AOP 132+133+137+141+142+143+146+153)",
      "positionProperties":[
         {
            "name":"1. Promjene vrijednosti zaliha proizvodnje u tijeku i gotovih proizvoda",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Materijalni troškovi (AOP 134 do 136)",
            "positionProperties":[
               {
                  "name":"a) Troškovi sirovina i materijala",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Troškovi prodane robe",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Ostali vanjski troškovi",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Troškovi osoblja (AOP 138 do 140)",
            "positionProperties":[
               {
                  "name":"a) Neto plaće i nadnice",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Troškovi poreza i doprinosa iz plaća",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Doprinosi na plaće",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Amortizacija",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Ostali troškovi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Vrijednosna usklađenja (AOP 144+145)",
            "positionProperties":[
               {
                  "name":"a) dugotrajne imovine osim financijske imovine",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) kratkotrajne imovine osim financijske imovine",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Doprinosi na plaće",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Rezerviranja (AOP 147 do 152)",
            "positionProperties":[
               {
                  "name":"a) Rezerviranja za mirovine, otpremnine i slične obveze",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Rezerviranja za porezne obveze",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Rezerviranja za započete sudske sporove",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"d) Rezerviranja za troškove obnavljanja prirodnih bogatstava",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"e) Rezerviranja za troškove u jamstvenim rokovima",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"f) Druga rezerviranja",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"8. Ostali poslovni rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"III. FINANCIJSKI PRIHODI (AOP 155 do 164)",
      "positionProperties":[
         {
            "name":"1. Prihodi od ulaganja u udjele (dionice) poduzetnika unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Prihodi od ulaganja u udjele (dionice) društava povezanih sudjelujućim interesima",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Prihodi od ostalih dugotrajnih financijskih ulaganja i zajmova poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Ostali prihodi s osnove kamata iz odnosa s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Tečajne razlike i ostali financijski prihodi iz odnosa s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Prihodi od ostalih dugotrajnih financijskih ulaganja i zajmova",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Ostali prihodi s osnove kamata",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"8. Tečajne razlike i ostali financijski prihodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"9. Nerealizirani dobici (prihodi) od financijske imovine",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"10. Ostali financijski prihodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"IV. FINANCIJSKI RASHODI (AOP 166 do 172)",
      "positionProperties":[
         {
            "name":"1. Rashodi s osnove kamata i slični rashodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Tečajne razlike i drugi rashodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Rashodi s osnove kamata i slični rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Tečajne razlike i drugi rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Nerealizirani gubici (rashodi) od financijske imovine",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Vrijednosna usklađenja financijske imovine (neto)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Ostali financijski rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"V.    UDIO U DOBITI OD DRUŠTAVA POVEZANIH SUDJELUJUĆIM INTERESOM",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VI.   UDIO U DOBITI OD  ZAJEDNIČKIH POTHVATA",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VII.  UDIO U GUBITKU OD DRUŠTAVA POVEZANIH SUDJELUJUĆIM INTERESOM",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VIII. UDIO U GUBITKU OD ZAJEDNIČKIH POTHVATA",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"   IX. UKUPNI PRIHODI (AOP 125+154+173 + 174)",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"X.   UKUPNI RASHODI (AOP 131+165+175 + 176)",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XI.   DOBIT ILI GUBITAK PRIJE OPOREZIVANJA (AOP 177-178)",
      "positionProperties":[
         {
            "name":"1. Dobit prije oporezivanja (AOP 177-178)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Gubitak prije oporezivanja (AOP 178-177)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XII.  POREZ NA DOBIT",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XIII. DOBIT ILI GUBITAK RAZDOBLJA (AOP 179-182)",
      "positionProperties":[
         {
            "name":"1. Dobit razdoblja (AOP 179-182)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Gubitak razdoblja (AOP 182-179)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   }
]');

insert into position_template (sheet_type, position_properties)
values('balanceSheet', '[
    {
        "name": "Active",
        "positionProperties": [
            {
                "name": "A)  POTRAŽIVANJA ZA UPISANI A NEUPLAĆENI KAPITAL",
                "positionProperties": [
                    {
                        "name": "A)  POTRAŽIVANJA ZA UPISANI A NEUPLAĆENI KAPITAL",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [
                                    {
                                        "name": "2000 - neki naziv",
                                        "positionProperties": [],
                                        "konto": 2000
                                    }
                                ],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "B)  DUGOTRAJNA IMOVINA (AOP 003+010+020+031+036)",
                "positionProperties": [
                    {
                        "name": "I. NEMATERIJALNA IMOVINA (AOP 004 do 009)",
                        "positionProperties": [
                            {
                                "name": "1. Izdaci za razvoj",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Koncesije, patenti, licencije, robne i uslužne marke, softver i ostala prava",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Goodwill",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Predujmovi za nabavu nematerijalne imovine",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Nematerijalna imovina u pripremi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Ostala nematerijalna imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. MATERIJALNA IMOVINA (AOP 011 do 019)",
                        "positionProperties": [
                            {
                                "name": "1. Zemljište",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Građevinski objekti",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Postrojenja i oprema ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Alati, pogonski inventar i transportna imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Biološka imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Predujmovi za materijalnu imovinu",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Materijalna imovina u pripremi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Ostala materijalna imovina",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ulaganje u nekretnine",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. DUGOTRAJNA FINANCIJSKA IMOVINA (AOP 021 do 030)",
                        "positionProperties": [
                            {
                                "name": "1. Ulaganja u udjele (dionice) poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Ulaganja u ostale vrijednosne papire poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Dani zajmovi, depoziti i slično poduzetnicima unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4.Ulaganja u udjele (dionice) društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ulaganja u ostale vrijednosne papire društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dani zajmovi, depoziti i slično društvima povezanim sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Ulaganja u vrijednosne papire",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Dani zajmovi, depoziti i slično",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ostala ulaganja koja se obračunavaju metodom udjela",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10.  Ostala dugotrajna financijska imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. POTRAŽIVANJA (AOP 032 do 035)",
                        "positionProperties": [
                            {
                                "name": "1. Potraživanja od poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Potraživanja od društava povezanih sudjelujućim interesom ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Potraživanja od kupaca ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Ostala potraživanja",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "V. ODGOĐENA POREZNA IMOVINA",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "C)  KRATKOTRAJNA IMOVINA (AOP 038+046+053+063)",
                "positionProperties": [
                    {
                        "name": "I. ZALIHE (AOP 039 do 045)",
                        "positionProperties": [
                            {
                                "name": "1. Sirovine i materijal",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Proizvodnja u tijeku",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Gotovi proizvodi",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Trgovačka roba",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Predujmovi za zalihe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dugotrajna imovina namijenjena prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Biološka imovina",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. POTRAŽIVANJA (AOP 047 do 052)",
                        "positionProperties": [
                            {
                                "name": "1. Potraživanja od poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Potraživanja od društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Potraživanja od kupaca K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Potraživanja od zaposlenika i članova poduzetnika",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Potraživanja od države i drugih institucija",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dugotrajna imovina namijenjena prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Ostala potraživanja K",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. KRATKOTRAJNA FINANCIJSKA IMOVINA (AOP 054 do 062)",
                        "positionProperties": [
                            {
                                "name": "1. Ulaganja u udjele (dionice) poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Ulaganja u ostale vrijednosne papire poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Dani zajmovi, depoziti i slično poduzetnicima unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Ulaganja u udjele (dionice) društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ulaganja u ostale vrijednosne papire društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Dani zajmovi, depoziti i slično društvima povezanim sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Ulaganja u vrijednosne papire K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Dani zajmovi, depoziti i slično K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Ostala financijska imovina K",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. NOVAC U BANCI I BLAGAJNI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "D)  PLAĆENI TROŠKOVI BUDUĆEG RAZDOBLJA I OBRAČUNATI PRIHODI",
                "positionProperties": [
                    {
                        "name": "D)  PLAĆENI TROŠKOVI BUDUĆEG RAZDOBLJA I OBRAČUNATI PRIHODI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "E)  UKUPNO AKTIVA (AOP 001+002+037+064)",
                "positionProperties": [
                    {
                        "name": "E)  UKUPNO AKTIVA (AOP 001+002+037+064)",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "F)  IZVANBILANČNI ZAPISI",
                "positionProperties": [
                    {
                        "name": "F)  IZVANBILANČNI ZAPISI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            }
        ]
    },
    {
        "name": "Pasive",
        "positionProperties": [
            {
                "name": "A)  KAPITAL I REZERVE (AOP 068 do 070+076+077+081+084+087)",
                "positionProperties": [
                    {
                        "name": "I. TEMELJNI (UPISANI) KAPITAL",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "II. KAPITALNE REZERVE",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "III. REZERVE IZ DOBITI (AOP 071+072-073+074+075)",
                        "positionProperties": [
                            {
                                "name": "1. Zakonske rezerve",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Rezerve za vlastite dionice",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Vlastite dionice i udjeli (odbitna stavka)",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Statutarne rezerve",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Ostale rezerve",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "IV. REVALORIZACIJSKE REZERVE",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "V. REZERVE FER VRIJEDNOSTI (AOP 078 do 080)",
                        "positionProperties": [
                            {
                                "name": "1. Fer vrijednost financijske imovine raspoložive za prodaju",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Učinkoviti dio zaštite novčanih tokova",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Učinkoviti dio zaštite neto ulaganja u inozemstvu",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VI. ZADRŽANA DOBIT ILI PRENESENI GUBITAK (AOP 082-083)",
                        "positionProperties": [
                            {
                                "name": "1. Zadržana dobit",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Preneseni gubitak",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VII. DOBIT ILI GUBITAK POSLOVNE GODINE (AOP 085-086)",
                        "positionProperties": [
                            {
                                "name": "1. Dobit poslovne godine",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Gubitak poslovne godine",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    },
                    {
                        "name": "VIII. MANJINSKI (NEKONTROLIRAJUĆI) INTERES",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "B)  REZERVIRANJA (AOP 089 do 094)",
                "positionProperties": [
                    {
                        "name": "REZERVIRANJA",
                        "positionProperties": [
                            {
                                "name": "1. Rezerviranja za mirovine, otpremnine i slične obvez",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Rezerviranja za porezne obveze",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Rezerviranja za započete sudske sporove",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Rezerviranja za troškove obnavljanja prirodnih bogatstava",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Rezerviranja za troškove u jamstvenim rokovima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Druga rezerviranja",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "C)  DUGOROČNE OBVEZE (AOP 096 do 106)",
                "positionProperties": [
                    {
                        "name": "DUGOROČNE OBVEZE",
                        "positionProperties": [
                            {
                                "name": "1. Obveze prema poduzetnicima unutar grupe ",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Obveze za zajmove, depozite i slično poduzetnika unutar grupe",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Obveze prema društvima povezanim sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Obveze za zajmove, depozite i slično društava povezanih sudjelujućim interesom",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Obveze za zajmove, depozite i slično",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Obveze prema bankama i drugim financijskim institucijama",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Obveze za predujmove",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Obveze prema dobavljačima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Obveze po vrijednosnim papirima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10. Ostale dugoročne obveze",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "11. Odgođena porezna obveza",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "D)  KRATKOROČNE OBVEZE (AOP 108 do 121)",
                "positionProperties": [
                    {
                        "name": "KRATKOROČNE OBVEZE",
                        "positionProperties": [
                            {
                                "name": "1. Obveze prema poduzetnicima unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "2. Obveze za zajmove, depozite i slično poduzetnika unutar grupe K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "3. Obveze prema društvima povezanim sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "4. Obveze za zajmove, depozite i slično društava povezanih sudjelujućim interesom K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "5. Obveze za zajmove, depozite i slično K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "6. Obveze prema bankama i drugim financijskim institucijama K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "7. Obveze za predujmove K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "8. Obveze prema dobavljačima K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "9. Obveze po vrijednosnim papirima K",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "10. Obveze prema zaposlenicima",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "11. Obveze  za poreze, doprinose i sličana davanja",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "12. Obveze s osnove udjela u rezultatu",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "13. Obveze po osnovi dugotrajne imovine namijenjene prodaji",
                                "positionProperties": [],
                                "konto": null
                            },
                            {
                                "name": "14. Ostale kratkoročne obveze",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "E) ODGOĐENO PLAĆANJE TROŠKOVA I PRIHOD BUDUĆEGA RAZDOBLJA",
                "positionProperties": [
                    {
                        "name": "ODGOĐENO PLAĆANJE TROŠKOVA I PRIHOD BUDUĆEGA RAZDOBLJA",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "F) UKUPNO – PASIVA (AOP 067+088+095+107+122)",
                "positionProperties": [
                    {
                        "name": "UKUPNO – PASIVA (AOP 067+088+095+107+122)",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            },
            {
                "name": "G)  IZVANBILANČNI ZAPISI",
                "positionProperties": [
                    {
                        "name": "IZVANBILANČNI ZAPISI",
                        "positionProperties": [
                            {
                                "name": "konto",
                                "positionProperties": [],
                                "konto": null
                            }
                        ],
                        "konto": null
                    }
                ],
                "konto": null
            }
        ]
    }
]');

insert into position_template (sheet_type, position_properties)
values('profitLoss', '[
   {
      "name":"I. POSLOVNI PRIHODI (AOP 126 do 130)",
      "positionProperties":[
         {
            "name":"1. Prihodi od prodaje s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Prihodi od prodaje (izvan grupe)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Prihodi na temelju upotrebe vlastitih proizvoda, robe i usluga",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Ostali poslovni prihodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Ostali poslovni prihodi (izvan grupe)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"II. POSLOVNI RASHODI (AOP 132+133+137+141+142+143+146+153)",
      "positionProperties":[
         {
            "name":"1. Promjene vrijednosti zaliha proizvodnje u tijeku i gotovih proizvoda",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Materijalni troškovi (AOP 134 do 136)",
            "positionProperties":[
               {
                  "name":"a) Troškovi sirovina i materijala",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Troškovi prodane robe",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Ostali vanjski troškovi",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Troškovi osoblja (AOP 138 do 140)",
            "positionProperties":[
               {
                  "name":"a) Neto plaće i nadnice",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Troškovi poreza i doprinosa iz plaća",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Doprinosi na plaće",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Amortizacija",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Ostali troškovi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Vrijednosna usklađenja (AOP 144+145)",
            "positionProperties":[
               {
                  "name":"a) dugotrajne imovine osim financijske imovine",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) kratkotrajne imovine osim financijske imovine",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Doprinosi na plaće",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Rezerviranja (AOP 147 do 152)",
            "positionProperties":[
               {
                  "name":"a) Rezerviranja za mirovine, otpremnine i slične obveze",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"b) Rezerviranja za porezne obveze",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"c) Rezerviranja za započete sudske sporove",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"d) Rezerviranja za troškove obnavljanja prirodnih bogatstava",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"e) Rezerviranja za troškove u jamstvenim rokovima",
                  "positionProperties":[

                  ],
                  "konto":null
               },
               {
                  "name":"f) Druga rezerviranja",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"8. Ostali poslovni rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"III. FINANCIJSKI PRIHODI (AOP 155 do 164)",
      "positionProperties":[
         {
            "name":"1. Prihodi od ulaganja u udjele (dionice) poduzetnika unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Prihodi od ulaganja u udjele (dionice) društava povezanih sudjelujućim interesima",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Prihodi od ostalih dugotrajnih financijskih ulaganja i zajmova poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Ostali prihodi s osnove kamata iz odnosa s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Tečajne razlike i ostali financijski prihodi iz odnosa s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Prihodi od ostalih dugotrajnih financijskih ulaganja i zajmova",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Ostali prihodi s osnove kamata",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"8. Tečajne razlike i ostali financijski prihodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"9. Nerealizirani dobici (prihodi) od financijske imovine",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"10. Ostali financijski prihodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"IV. FINANCIJSKI RASHODI (AOP 166 do 172)",
      "positionProperties":[
         {
            "name":"1. Rashodi s osnove kamata i slični rashodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Tečajne razlike i drugi rashodi s poduzetnicima unutar grupe",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"3. Rashodi s osnove kamata i slični rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"4. Tečajne razlike i drugi rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"5. Nerealizirani gubici (rashodi) od financijske imovine",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"6. Vrijednosna usklađenja financijske imovine (neto)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"7. Ostali financijski rashodi",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"V.    UDIO U DOBITI OD DRUŠTAVA POVEZANIH SUDJELUJUĆIM INTERESOM",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VI.   UDIO U DOBITI OD  ZAJEDNIČKIH POTHVATA",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VII.  UDIO U GUBITKU OD DRUŠTAVA POVEZANIH SUDJELUJUĆIM INTERESOM",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"VIII. UDIO U GUBITKU OD ZAJEDNIČKIH POTHVATA",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"   IX. UKUPNI PRIHODI (AOP 125+154+173 + 174)",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"X.   UKUPNI RASHODI (AOP 131+165+175 + 176)",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XI.   DOBIT ILI GUBITAK PRIJE OPOREZIVANJA (AOP 177-178)",
      "positionProperties":[
         {
            "name":"1. Dobit prije oporezivanja (AOP 177-178)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Gubitak prije oporezivanja (AOP 178-177)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XII.  POREZ NA DOBIT",
      "positionProperties":[
         {
            "name":"Konto",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   },
   {
      "name":"XIII. DOBIT ILI GUBITAK RAZDOBLJA (AOP 179-182)",
      "positionProperties":[
         {
            "name":"1. Dobit razdoblja (AOP 179-182)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         },
         {
            "name":"2. Gubitak razdoblja (AOP 182-179)",
            "positionProperties":[
               {
                  "name":"Konto",
                  "positionProperties":[

                  ],
                  "konto":null
               }
            ],
            "konto":null
         }
      ],
      "konto":null
   }
]');

insert into financial_indicator_template (liquidity_indicators, indebtedness_indicators, activity_indicators, cost_effectiveness_indicators, profitability_indicators)
values('{
  "name": "Liquidity Indicators List",
  "propertiesList": [
    {
      "name": "koeficijent tekuće likvidnosti",
      "action": "BS/BS",
      "firstIndex": [
        0,
        2
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    },
    {
      "name": "koeficijent ubrzane likvidnosti",
      "action": "BS/BS",
      "firstIndex": [
        0,
        2,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    },
    {
      "name": "koeficijent trenutne likvidnosti",
      "action": "BS/BS",
      "firstIndex": [
        0,
        2,
        3
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    },
    {
      "name": "koeficijent financijske stabilnost",
      "action": "BS/BS+BS",
      "firstIndex": [
        0,
        1
      ],
      "thirdIndex": [
        0,
        2
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        0
      ],
      "percentage": false
    },
    {
      "name": "neto obrtni kapital",
      "action": "BS-BS",
      "firstIndex": [
        0,
        2
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    }
  ]
}', '{
  "name": "Pokazatelji zaduženosti",
  "propertiesList": [
    {
      "name": "koeficijent zaduženosti",
      "action": "BS+BS/BS",
      "firstIndex": [
        1,
        2
      ],
      "thirdIndex": [
        0,
        4
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": true
    },
    {
      "name": "koeficijent vlastitog financiranja",
      "action": "BS/BS",
      "firstIndex": [
        0,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        4
      ],
      "percentage": true
    },
    {
      "name": "odnos duga i glavnice",
      "action": "BS+BS/BS",
      "firstIndex": [
        1,
        2
      ],
      "thirdIndex": [
        1,
        0
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    },
    {
      "name": "stupanj pokrića I",
      "action": "BS*100/BS",
      "firstIndex": [
        1,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        1
      ],
      "percentage": false
    },
    {
      "name": "stupanj pokrića II",
      "action": "BS+BS/BS",
      "firstIndex": [
        1,
        0
      ],
      "thirdIndex": [
        0,
        1
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        2
      ],
      "percentage": false
    }
  ]
}', '{
  "name": "Pokazatelji aktivnosti",
  "propertiesList": [
    {
      "name": "koeficijent obrta ukupne imovine",
      "action": "PL/BS",
      "firstIndex": [
        8
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        4
      ],
      "percentage": false
    },
    {
      "name": "koeficijent obrta dugotrajne imovine",
      "action": "PL/BS",
      "firstIndex": [
        8
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        1
      ],
      "percentage": false
    },
    {
      "name": "koeficijent obrta kratkotrajne imovine",
      "action": "PL/BS",
      "firstIndex": [
        8
      ],
      "thirdIndex": [
        1,
        0
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        2
      ],
      "percentage": false
    },
    {
      "name": "Koeficijent obrta zaliha",
      "action": "PL/BS",
      "firstIndex": [
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        2,
        0
      ],
      "percentage": false
    },
    {
      "name": "Dani vezivanja zaliha",
      "action": "BS/PL+PL",
      "firstIndex": [
        0,
        2,
        1
      ],
      "thirdIndex": [
        1,
        1,
        1
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        1,
        0
      ],
      "percentage": false
    },
    {
      "name": "Koeficijent obrta potraživanja",
      "action": "PL/BS",
      "firstIndex": [
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        1,
        3,
        2
      ],
      "percentage": false
    },
    {
      "name": "Dani naplate potraživanja",
      "action": "365/PL/BS",
      "firstIndex": [
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        1,
        3,
        2
      ],
      "percentage": false
    },
    {
      "name": "Koeficijent obrta obveza prema dobavljačima",
      "action": "PL/BS",
      "firstIndex": [
        1,
        1
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        2
      ],
      "percentage": false
    },
    {
      "name": "Dani plaćanja obveza prema dobavljačima",
      "action": "365/PL/BS",
      "firstIndex": [
        1,
        1
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        2
      ],
      "percentage": false
    }
  ]
}', '{
    "name": "Pokazatelji ekonomičnosti",
    "propertiesList": [
        {
            "name": "ekonomičnost ukupnog poslovanja",
            "action": "PL/PL",
            "firstIndex": [
                8
            ],
            "thirdIndex": [],
            "fourthIndex": [],
            "secondIndex": [
                9
            ],
"percentage": false

        },
        {
            "name": "ekonomičnost financiranja",
            "action": "PL/PL",
            "firstIndex": [
                2
            ],
            "thirdIndex": [],
            "fourthIndex": [],
            "secondIndex": [
                3
            ],
"percentage": false

        }
    ]
}', '{
  "name": "Pokazatelji profitabilnosti",
  "propertiesList": [
    {
      "name": "marža profita",
      "action": "PL/PL",
      "firstIndex": [
        12,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        8
      ],
      "percentage": true
    },
    {
      "name": "stopa povrata imovine ROA",
      "action": "PL/BS",
      "firstIndex": [
        12,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        4
      ],
      "percentage": true
    },
    {
      "name": "stopa povrata kapitala ROE",
      "action": "PL/BS",
      "firstIndex": [
        12,
        0
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        0
      ],
      "percentage": true
    },
    {
      "name": "WK / prihodi",
      "action": "BS-BS/PL",
      "firstIndex": [
        0,
        2
      ],
      "thirdIndex": [
        8
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        3
      ],
      "percentage": false
    },
    {
      "name": "Neto dug / EBITD",
      "action": "BS-BS/EBITDA",
      "firstIndex": [
        1,
        2,
        0,
        5
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        0,
        2,
        3
      ],
      "percentage": false
    },
    {
      "name": "ROCE",
      "action": "EBIT/(BS+BS",
      "firstIndex": [

      ],
      "thirdIndex": [
        1,
        2
      ],
      "fourthIndex": [

      ],
      "secondIndex": [
        1,
        0
      ],
      "percentage": false
    },
    {
      "name": "Trošak kamate / EBIT",
      "action": "PL/EBIT",
      "firstIndex": [
        3,
        2
      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [

      ],
      "percentage": false
    },
    {
      "name": "EBITDA marža",
      "action": "EBITDA",
      "firstIndex": [

      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [

      ],
      "percentage": true
    },
    {
      "name": "EBIT marža",
      "action": "EBIT",
      "firstIndex": [

      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [

      ],
      "percentage": true
    },
    {
      "name": "DSCR",
      "action": "EBITDA/INTEREST",
      "firstIndex": [

      ],
      "thirdIndex": [

      ],
      "fourthIndex": [

      ],
      "secondIndex": [

      ],
      "percentage": false
    }
  ]
}')
