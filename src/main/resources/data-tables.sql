INSERT INTO `status` (`id`, `label`) VALUES
(1, 'online'),
(2, 'offline'),
(3, 'away'),
(4, 'Do not disturb');

INSERT INTO `skill` (`id`,`label`) VALUES
(1, 'Developer'),
(2, 'Designer'),
(3, 'Graphic Designer'),
(4, 'Dev. FullStack');

INSERT INTO `user` (`id`, `firstname`, `name`, `status_id`) VALUES
(1,'Théo', 'Montaldo', 4),
(2,'Tom', 'Sawyer', 2),
(3,'Da way', 'Do u kno', 3);

INSERT INTO `user_skill` (`user_id`,`skill_id`) VALUES
(1, 3),
(1, 4),
(2, 3),
(2, 2),
(3, 4);