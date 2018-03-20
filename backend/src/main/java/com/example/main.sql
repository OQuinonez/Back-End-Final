CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    UserName text,
    UAddress text,
    Email text,
    HashCode text
);

CREATE TABLE Products(
    ItemID SERIAL PRIMARY KEY,
    ItemName text,
    Category text,
    Price float,
    Quantity Int,
    PicAddress text
);


-- Use the UserID and ItemID To Find which user and see
-- What Items the user has, and the total of the items
CREATE TABLE Cart(
    UserID Integer References Users(UserID),
    ItemID Integer References Products(ItemID)
);

INSERT INTO Users(UserName, UAddress, Email, HashCode) VALUES
('Oz Qui', '123 West Main Street', 'oquinonez@basecampcodingacademy.org', '23krjslajr3lk2jlf');

INSERT INTO Products(ItemName, Category, Price, Quantity, PicAddress) VALUES
('Marker', 'Arts', 2.99, 9, 'alksdfjlksadjfkalsjf');