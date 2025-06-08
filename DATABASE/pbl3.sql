-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2025 at 12:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbl3`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity_log`
--

CREATE TABLE `activity_log` (
  `idLog` int(11) NOT NULL,
  `idBoard` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `action` varchar(255) NOT NULL,
  `entityType` varchar(100) NOT NULL,
  `idEntity` int(11) NOT NULL,
  `dateCreated` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE `attachment` (
  `idAttachment` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idItem references Task (not just any Item)';

-- --------------------------------------------------------

--
-- Table structure for table `board`
--

CREATE TABLE `board` (
  `idBoard` int(11) NOT NULL,
  `idProject` int(11) DEFAULT NULL,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `board`
--

INSERT INTO `board` (`idBoard`, `idProject`, `dateCreated`, `dateModified`) VALUES
(1, 1, '2025-06-06 11:15:23', '2025-06-06 11:15:23');

-- --------------------------------------------------------

--
-- Table structure for table `checklist`
--

CREATE TABLE `checklist` (
  `idCheckList` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `checklist_item`
--

CREATE TABLE `checklist_item` (
  `idCLItem` int(11) NOT NULL,
  `idCheckList` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT 0 COMMENT '0: false\r\n1: true'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `idComment` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  `content` text DEFAULT NULL,
  `dateCreated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idItem references Task (not just any Item)';

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`idComment`, `idTask`, `idUser`, `content`, `dateCreated`) VALUES
(1, 5, 9, 'This is a new comment.', '2025-06-06 15:18:45'),
(2, 5, 9, 'This is a new comment.', '2025-06-06 15:19:40'),
(3, 5, 9, 'This is a new comment.', '2025-06-06 17:43:54');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `idItem` int(11) NOT NULL,
  `idBacklog` int(11) NOT NULL,
  `backlogType` tinyint(1) NOT NULL COMMENT '0: idProductBacklog\r\n1: idSprint',
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '0: Not done\r\n1: Done'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idBacklog can reference either ProductBacklog.idProductBacklog or SprintBacklog.idSBacklog';

-- --------------------------------------------------------

--
-- Table structure for table `label`
--

CREATE TABLE `label` (
  `idLabel` int(11) NOT NULL,
  `idBoard` int(11) NOT NULL,
  `labelName` varchar(255) DEFAULT NULL,
  `color` varchar(255) NOT NULL DEFAULT '#FFFFFF'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE `meeting` (
  `idMeeting` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `meeting_user`
--

CREATE TABLE `meeting_user` (
  `idMeeting` int(11) NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `idNotification` int(11) NOT NULL,
  `idSender` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification_receiver`
--

CREATE TABLE `notification_receiver` (
  `idNotification` int(11) NOT NULL,
  `idReceiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_backlog`
--

CREATE TABLE `product_backlog` (
  `idProductBacklog` int(11) NOT NULL,
  `idProject` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `idProject` int(11) NOT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateModified` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL COMMENT '0: Not done\r\n1: Done',
  `isDisabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0: false\r\n1: true'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`idProject`, `projectName`, `description`, `dateCreated`, `dateModified`, `status`, `isDisabled`) VALUES
(1, 'Test project 1', '', '2025-06-06 11:15:23', '2025-06-06 00:00:00', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `sprint`
--

CREATE TABLE `sprint` (
  `idSprint` int(11) NOT NULL,
  `idProject` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `dateStart` datetime DEFAULT NULL,
  `estimatedEndDate` date NOT NULL,
  `actualEndDate` date NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `idTask` int(11) NOT NULL,
  `idTaskList` int(11) DEFAULT NULL,
  `idLabel` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  `dateDue` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL COMMENT '0: false\r\n1: true',
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idItem is both PK and FK to Item';

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`idTask`, `idTaskList`, `idLabel`, `title`, `description`, `dateCreated`, `dateModified`, `dateDue`, `status`, `position`) VALUES
(1, 1, NULL, 'Task 1', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 0),
(2, 1, NULL, 'Task 2', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 1),
(3, 1, NULL, 'Task 3', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 2),
(4, 2, NULL, 'Task 1', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 0),
(5, 2, NULL, 'Updated Task Name', 'Updated Task Description', '2025-06-06 11:15:23', '2025-06-06 15:02:07', '2025-06-06 15:02:07', 1, 1),
(6, 2, NULL, 'Task 3', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 2),
(7, 3, NULL, 'Task 1', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 0),
(8, 3, NULL, 'Task 2', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 1),
(9, 3, NULL, 'Task 3', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', NULL, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `task_label`
--

CREATE TABLE `task_label` (
  `idTask` int(11) DEFAULT NULL,
  `idLabel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `task_list`
--

CREATE TABLE `task_list` (
  `idTaskList` int(11) NOT NULL,
  `idBoard` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `dateCreated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task_list`
--

INSERT INTO `task_list` (`idTaskList`, `idBoard`, `title`, `position`, `dateCreated`) VALUES
(1, 1, 'To Do', 0, '2025-06-06 11:15:23'),
(2, 1, 'In Progress', 1, '2025-06-06 11:15:23'),
(3, 1, 'Done', 2, '2025-06-06 11:15:23');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `role` tinyint(1) DEFAULT 2 COMMENT '0: System\r\n1: Admin\r\n2: PO\r\n3: SM\r\n4: DT',
  `birthday` date DEFAULT NULL,
  `englishName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT 'src/main/resources/ImageAvatar.png',
  `isDisabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0: false\r\n1: true'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `email`, `fullName`, `role`, `birthday`, `englishName`, `address`, `phone`, `avatar`, `isDisabled`) VALUES
(1, 'System', '', '', '', 0, '0000-00-00', '', '', '', '', 0),
(2, 'admin', 'admin', '', '', 1, '0000-00-00', '', '', '', '', 0),
(3, 'testuser', '123456789', 'testemail@gmail.com', 'Test User', 2, '0000-00-00', 'testuser', '1 testuser St.', '0000111222', '', 0),
(9, 'testingagain', 'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', NULL, NULL, 2, '0000-00-00', NULL, NULL, NULL, 'src/main/resources/ImageAvatar.png', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_project`
--

CREATE TABLE `user_project` (
  `idUserProject` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idProject` int(11) DEFAULT NULL,
  `relationship` tinyint(1) DEFAULT 0 COMMENT '0: creator\r\n1: member'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Composite PK (idUser, idProject)';

--
-- Dumping data for table `user_project`
--

INSERT INTO `user_project` (`idUserProject`, `idUser`, `idProject`, `relationship`) VALUES
(9, 9, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_task`
--

CREATE TABLE `user_task` (
  `idUserTask` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idTask` int(11) DEFAULT NULL,
  `relationship` tinyint(1) DEFAULT 1 COMMENT '0: assignor\r\n1: assignee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Composite PK (idUser, idTask)';

--
-- Dumping data for table `user_task`
--

INSERT INTO `user_task` (`idUserTask`, `idUser`, `idTask`, `relationship`) VALUES
(6, 9, 5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity_log`
--
ALTER TABLE `activity_log`
  ADD PRIMARY KEY (`idLog`),
  ADD KEY `idBoard` (`idBoard`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `attachment`
--
ALTER TABLE `attachment`
  ADD PRIMARY KEY (`idAttachment`),
  ADD KEY `attachment_ibfk_1` (`idTask`);

--
-- Indexes for table `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`idBoard`),
  ADD KEY `idProject` (`idProject`);

--
-- Indexes for table `checklist`
--
ALTER TABLE `checklist`
  ADD PRIMARY KEY (`idCheckList`),
  ADD KEY `idTask` (`idTask`);

--
-- Indexes for table `checklist_item`
--
ALTER TABLE `checklist_item`
  ADD PRIMARY KEY (`idCLItem`),
  ADD KEY `idCheckList` (`idCheckList`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`idComment`),
  ADD KEY `comment_ibfk_1` (`idTask`),
  ADD KEY `comment_ibfk_2` (`idUser`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`),
  ADD KEY `idBacklog` (`idBacklog`);

--
-- Indexes for table `label`
--
ALTER TABLE `label`
  ADD PRIMARY KEY (`idLabel`),
  ADD KEY `label_ibfk_1` (`idBoard`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`idMeeting`);

--
-- Indexes for table `meeting_user`
--
ALTER TABLE `meeting_user`
  ADD KEY `meeting_user_ibfk_1` (`idMeeting`),
  ADD KEY `meeting_user_ibfk_2` (`idUser`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`idNotification`),
  ADD KEY `idSender` (`idSender`);

--
-- Indexes for table `notification_receiver`
--
ALTER TABLE `notification_receiver`
  ADD KEY `notification_receiver_ibfk_1` (`idNotification`),
  ADD KEY `notification_receiver_ibfk_2` (`idReceiver`);

--
-- Indexes for table `product_backlog`
--
ALTER TABLE `product_backlog`
  ADD PRIMARY KEY (`idProductBacklog`),
  ADD KEY `idProject` (`idProject`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`idProject`);

--
-- Indexes for table `sprint`
--
ALTER TABLE `sprint`
  ADD PRIMARY KEY (`idSprint`),
  ADD KEY `idProject` (`idProject`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`idTask`),
  ADD KEY `task_ibfk_2` (`idTask`),
  ADD KEY `idList` (`idTaskList`),
  ADD KEY `task_ibfk_3` (`idLabel`);

--
-- Indexes for table `task_label`
--
ALTER TABLE `task_label`
  ADD KEY `tasklabel_ibfk_1` (`idLabel`),
  ADD KEY `tasklabel_ibfk_2` (`idTask`);

--
-- Indexes for table `task_list`
--
ALTER TABLE `task_list`
  ADD PRIMARY KEY (`idTaskList`),
  ADD KEY `idBoard` (`idBoard`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- Indexes for table `user_project`
--
ALTER TABLE `user_project`
  ADD PRIMARY KEY (`idUserProject`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idProject` (`idProject`);

--
-- Indexes for table `user_task`
--
ALTER TABLE `user_task`
  ADD PRIMARY KEY (`idUserTask`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `usertask_ibfk_2` (`idTask`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity_log`
--
ALTER TABLE `activity_log`
  MODIFY `idLog` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `board`
--
ALTER TABLE `board`
  MODIFY `idBoard` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `checklist`
--
ALTER TABLE `checklist`
  MODIFY `idCheckList` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `checklist_item`
--
ALTER TABLE `checklist_item`
  MODIFY `idCLItem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `idComment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `idItem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `label`
--
ALTER TABLE `label`
  MODIFY `idLabel` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
  MODIFY `idMeeting` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `idNotification` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `idProject` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sprint`
--
ALTER TABLE `sprint`
  MODIFY `idSprint` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `idTask` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `task_list`
--
ALTER TABLE `task_list`
  MODIFY `idTaskList` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_project`
--
ALTER TABLE `user_project`
  MODIFY `idUserProject` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_task`
--
ALTER TABLE `user_task`
  MODIFY `idUserTask` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity_log`
--
ALTER TABLE `activity_log`
  ADD CONSTRAINT `activity_log_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `activity_log_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Constraints for table `board`
--
ALTER TABLE `board`
  ADD CONSTRAINT `board_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Constraints for table `checklist`
--
ALTER TABLE `checklist`
  ADD CONSTRAINT `checklist_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Constraints for table `checklist_item`
--
ALTER TABLE `checklist_item`
  ADD CONSTRAINT `checklist_item_ibfk_1` FOREIGN KEY (`idCheckList`) REFERENCES `checklist` (`idCheckList`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`idBacklog`) REFERENCES `product_backlog` (`idProductBacklog`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`idBacklog`) REFERENCES `sprint` (`idSprint`);

--
-- Constraints for table `label`
--
ALTER TABLE `label`
  ADD CONSTRAINT `label_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`);

--
-- Constraints for table `meeting_user`
--
ALTER TABLE `meeting_user`
  ADD CONSTRAINT `meeting_user_ibfk_1` FOREIGN KEY (`idMeeting`) REFERENCES `meeting` (`idMeeting`),
  ADD CONSTRAINT `meeting_user_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`idSender`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `notification_receiver`
--
ALTER TABLE `notification_receiver`
  ADD CONSTRAINT `notification_receiver_ibfk_1` FOREIGN KEY (`idNotification`) REFERENCES `notification` (`idNotification`),
  ADD CONSTRAINT `notification_receiver_ibfk_2` FOREIGN KEY (`idReceiver`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `product_backlog`
--
ALTER TABLE `product_backlog`
  ADD CONSTRAINT `product_backlog_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Constraints for table `sprint`
--
ALTER TABLE `sprint`
  ADD CONSTRAINT `sprint_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`idTaskList`) REFERENCES `task_list` (`idTaskList`),
  ADD CONSTRAINT `task_ibfk_3` FOREIGN KEY (`idLabel`) REFERENCES `label` (`idLabel`);

--
-- Constraints for table `task_label`
--
ALTER TABLE `task_label`
  ADD CONSTRAINT `task_label_ibfk_1` FOREIGN KEY (`idLabel`) REFERENCES `label` (`idLabel`),
  ADD CONSTRAINT `task_label_ibfk_2` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Constraints for table `task_list`
--
ALTER TABLE `task_list`
  ADD CONSTRAINT `task_list_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`);

--
-- Constraints for table `user_project`
--
ALTER TABLE `user_project`
  ADD CONSTRAINT `user_project_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `user_project_ibfk_2` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Constraints for table `user_task`
--
ALTER TABLE `user_task`
  ADD CONSTRAINT `user_task_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `user_task_ibfk_2` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
