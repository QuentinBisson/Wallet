@/* Clear the screen */;
clear;
@/* This means less typing. If a script is automated, or is not meant to be interactive, use this command */;
set ACCEPT_DEFAULTS true;
@/* Enable CDI if not already done */;
beans setup;
ejb setup;
jta setup;

@/* Entity StockExchange*/;
entity --named StockExchange --package ~.model.entities;
field string --named name;
field manyToMany --named companies --fieldType ~.model.entities.Company.java;
field oneToMany --named options --fieldType ~.model.entities.StockOption.java;
cd ..;

@/* Entity StockOption*/;
entity --named StockOption --package ~.model.entities;
field double --named boughtValue;
field manyToOne --named options --fieldType ~.model.entities.StockOption.java;
field manyToOne --named company --fieldType ~.model.entities.Company.java;
cd ..;

@/* Entity History*/;
entity --named History --package ~.model.entities;
field double --named value;
field manyToOne --named company --fieldType ~.model.entities.Company.java;
cd ..;

@/* Entity Company*/;
entity --named Company --package ~.model.entities;
field string --named code;
field string --named name;
field string --named sector;
field temporal --type DATE --named creationDate;
field oneToMany --named history --fieldType ~.model.entities.History.java;
field oneToMany --named options --fieldType ~.model.entities.StockOption.java;
cd ..;

@/* Entity Wallet*/;
entity --named Wallet --package ~.model.entities;
field oneToOne --named user --fieldType ~.model.entities.User.java;
field oneToMany --named stockOptions --fieldType ~.model.entities.StockOption.java;
cd ..;

@/* Entity User*/;
entity --named User --package ~.model.entities;
field string --named username;
field string --named password;
field string --named salt;
field temporal --type DATE --named lastConnection;
cd ..;

@/* Entity Administrator*/;
entity --named Administrator;
cd ..;

@/* Entity Client*/;
entity --named Client;
field string --named firstName;
field string --named lastName;
field oneToOne --named wallet --fieldType ~.model.entities.Wallet.java;
cd ..;

@/* Generate the UI for all of our @Entities at once */;
scaffold from-entity ~.domain.* --scaffoldType faces --overwrite;
cd ~~;

ejb new-ejb --named UserEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named ClientEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named WalletEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named StockExchangeEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named StockOptionEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named HistoryEjb --type STATELESS --package ~.model.ejb;
ejb new-ejb --named CompanyEjb --type STATELESS --package ~.model.ejb;
cd ..;

set ACCEPT_DEFAULTS false;
@/* Return to the project root directory and leave it in your hands */;
cd ~~;