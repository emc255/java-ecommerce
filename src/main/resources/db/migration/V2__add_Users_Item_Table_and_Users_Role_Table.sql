CREATE TABLE User_Item(
    id SERIAL,
    userId INT NOT NULL,
    itemId INT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_users
        FOREIGN KEY (userId)
            REFERENCES Users(id),
    CONSTRAINT fk_item
        FOREIGN KEY (itemId)
            REFERENCES Item(id)
);

CREATE TABLE User_Role(
    id SERIAL,
    userId INT NOT NULL,
    roleId INT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_users
        FOREIGN KEY (userId)
            REFERENCES Users(id),
    CONSTRAINT fk_role
        FOREIGN KEY (roleId)
            REFERENCES Role(id)
);

--USER_ROLE
INSERT INTO public.user_role(
    userId, roleId)
    VALUES (1, 1);

INSERT INTO public.user_role(
        userId, roleId)
        VALUES (1, 3);

INSERT INTO public.user_role(
    userId, roleId)
    VALUES (2, 2);

INSERT INTO public.user_role(
        userId, roleId)
        VALUES (2, 3);

INSERT INTO public.user_role(
    userId, roleId)
    VALUES (3, 3);
