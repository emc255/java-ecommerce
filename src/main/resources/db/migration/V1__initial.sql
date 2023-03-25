CREATE TABLE Brand(
    id SERIAL,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE Item(
    id SERIAL,
    brandId INT NOT NULL,
    model VARCHAR(100),
    year INT,
    type VARCHAR(100),
    description VARCHAR(5000),
    price DOUBLE PRECISION,
    quantity int,
    PRIMARY KEY(id),
       CONSTRAINT fk_brand
          FOREIGN KEY(brandId)
    	  REFERENCES Brand(id)
);

CREATE TABLE Role(
    id SERIAL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Users(
    id SERIAL,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE Item_Image(
    id SERIAL,
    itemId INT NOT NULL,
    image VARCHAR(2000)
);

--BRANDS
INSERT INTO public.brand(
	name, phone)
	VALUES ('SUBARU', '9991112244');

INSERT INTO public.brand(
	name, phone)
	VALUES ('TOYOTA', '8881112222');

INSERT INTO public.brand(
	name, phone)
	VALUES ('HONDA', '8884442552');

--VEHICLES
INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (3, 'CRV', 2018, 'SUV','Honda CR-V SUV. It delivers energetic acceleration and a comfortable ride, and it gets good gas mileage. The interior looks and feels upscale. Both rows of seats are spacious and comfortable, and the cargo hold is one of the largest in the compact SUV class. This Honda’s only real weaknesses are its short standard features list in the base trim and its moderately frustrating infotainment controls.', 22000.50, 10);

INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (2, 'Rav4', 2019, 'SUV','RAV4 ignites your desire to explore. Choose from a variety of models that are as unique as you are. Including the 2021 RAV4 Limited, off-road ready RAV4 Trail or RAV4 TRD Off-Road, and the powerful and sporty RAV4 Hybrid. Or, embrace the cutting-edge, with the first ever RAV4 plug-in hybrid electric, the 2021 RAV4 Prime - the most powerful and fuel-efficient RAV4 yet.', 24000.25,10);

INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (1, 'Outback', 2020, 'SUV','Designed for unlimited adventure and uncompromising safety, the 2022 Outback features the confidence of standard Symmetrical All-Wheel Drive with up to 33 MPG** and the advanced protection of both standard EyeSight® Driver Assist Technology[1] and the available DriverFocus® Distraction Mitigation System[2]. And it’s engineered to last, with 97% of Outback vehicles sold in the last 10 years still on the road today[3]. For 2022, an even higher level of Outback capability is now available: the all-new Subaru Outback Wilderness™, featuring extensive all-terrain upgrades including an even more surefooted drivetrain, increased ground clearance, rugged exterior cladding, and a stylish, unique interior.', 28500.46, 10);

INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (1, 'WRX STI', 2022, 'Sports Car','Presenting the 2021 Subaru WRX and WRX STI, the latest versions of the performance sedans with serious motorsport credentials and legendary all-road/all-weather capability. Forging their own path in a sea of similarity, this dynamic duo showcases the core technology that has created diehard followers around the world — namely, the turbocharged SUBARU BOXER® engines and Subaru Symmetrical Full-Time AWD. Over the years, the engineering has been fine-tuned for even greater levels of driver engagement. Both cars currently feature powerful brake systems while the WRX utilises a strengthened manual transmission. The more hardcore WRX STI features an electronic Driver Controlled Centre Differential for faster response and improved cornering capability. These unique sedans also boast aggressive exterior styling and sporty interior accents to go along with surprising everyday comfort, cargo space, safety and versatility.', 15400.67, 10);

INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (2, 'GR SUPRA', 2022, 'Sports Car','Honed on the track. At home on the streets. The 2022 Toyota GR Supra serves up pure driving pleasure at every turn. Available with a choice of two turbocharged powertrains. With its front-engine / rear-wheel drive set-up, low centre of gravity, and racing-inspired interior, the GR Supra is ready to translate your every input into sheer exhilaration.', 36590.00, 10);

INSERT INTO public.item(
    brandId, model, year, type, description, price, quantity)
    VALUES (2, 'Tundra', 2022, 'Truck' ,'Forecast Ski and King Snow - Canada’s premier ski and snowboard magazines - and Toyota take you on another powder-packed outing in B.C.’s interior. This time, our skiers and boarders find fresh adventure in familiar stomping grounds.', 24000.00, 10);

--ITEM IMAGE
INSERT INTO public.item_image(
    itemId, image)
    VALUES (1, 'https://www.honda.ca/Content/honda.ca/883a99df-a5a6-437c-bff0-978a6bffbdcf/GenericContent_GC1/my20_CR-V_exterior_01vv.jpg');

INSERT INTO public.item_image(
    itemId, image)
    VALUES (2, 'https://img.sm360.ca/ir/w1024h768/images/newcar/ca/2021/toyota/rav4-prime/se/suv/2021_toyota_rav4-prime_se_001.png');

INSERT INTO public.item_image(
    itemId, image)
    VALUES (3,'https://www.subaru.ca/Content/7907/media/General/ImageLibrary/22OBK_Ltd_Tunnel_BBM_SOA_Medium.jpg' );

INSERT INTO public.item_image(
    itemId, image)
    VALUES (4,'https://www.subaru.ca/Content/7907/media/General/ImageLibrary/21WRX_STP_CVT_ES_Quarry_CWP_SOA_Medium.jpg' );

INSERT INTO public.item_image(
    itemId, image)
    VALUES (5, 'https://s3.amazonaws.com/toyota.site.toyota-v5/tci-prod/toyota/media/pages/catalogue/supra/2022/overview-v2/gallery/toyota-2022-supra-lineup-nitro-yellow-absolute-white-l.jpg?ck=09302021102947');

INSERT INTO public.item_image(
    itemId, image)
    VALUES (6, 'https://s3.amazonaws.com/toyota.site.toyota-v5/tci-prod/toyota/media/pages/catalogue/tundra/2021/overview-v2/gallery/toyota-2021-tundra-sr5-double-cab-midnight-black-metallic-l.jpg?ck=10152021080733');




--ROLES
INSERT INTO public.role(
	name)
	VALUES ('Admin');

INSERT INTO public.role(
	name)
	VALUES ('Employee');

INSERT INTO public.role(
	name)
	VALUES ('User');

--USERS
INSERT INTO public.users(
	name, username, password, email)
	VALUES ('Jihan', 'jihan', '$2a$10$SYycBOrirSU.bWORm5uAH.ZQZxWc5eH7Hng9y6O.JA9xPFi4yLsHm', 'email@email.com');

INSERT INTO public.users(
	name, username, password, email)
	VALUES ('Soojin', 'soojin', '$2a$12$dHN1NtSvDQSZbCciEtAPEu3IPAeHZRzt4e9AQaT57Bx22ZRE3UHG6', 'email@email.com');

INSERT INTO public.users(
	name, username, password, email)
	VALUES ('Zoa', 'zoa', '$2a$10$IplSisqXyVAcQP1ghBehsOHHwfQAJTIY7mwRMTpQzxH/hRGB04cOe', 'email@email.com');


