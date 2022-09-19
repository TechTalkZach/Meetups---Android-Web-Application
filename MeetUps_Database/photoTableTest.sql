INSERT INTO photo (photoURL, publicUserId) VALUES ("https://www.dmarge.com/wp-content/uploads/2021/01/dwayne-the-rock--768x512.jpg", 1);
INSERT INTO photo (photoURL, publicUserId) VALUES (null, 1);
INSERT INTO likes VALUES (1, 3, true);

 SELECT * 
 FROM publicUser
 WHERE id != 1 AND
 WHERE id NOT IN (
    SELECT toIdUser
    FROM likes
    WHERE fromIdUser = 1;
 );

SELECT * FROM publicUser
WHERE id IN(
 SELECT toIdUser FROM likes
 WHERE fromIdUser = 1 AND
 liked = true AND
 toIdUser IN (
   SELECT fromIdUser FROM likes
   WHERE toIdUser = 1 AND
   liked = true
 )
);

SELECT fromIdUser FROM likes
 WHERE toIdUser = 1 AND
 liked = true;

BEGIN
  DECLARE userThatLikedMe;
  DECLARE userThatILiked;
  DECLARE matches;

  SET userThatLikedMe = 
    SELECT fromIdUser FROM likes
    WHERE toIdUser = 1 AND
    liked = true;

  SELECT userThatLikedMe
    
END; 

 