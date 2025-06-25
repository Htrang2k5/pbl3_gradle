-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 09, 2025 lúc 05:34 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `pbl3`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `activity_log`
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
-- Cấu trúc bảng cho bảng `attachment`
--

CREATE TABLE `attachment` (
  `idAttachment` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idItem references Task (not just any Item)';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `board`
--

CREATE TABLE `board` (
  `idBoard` int(11) NOT NULL,
  `idProject` int(11) DEFAULT NULL,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `board`
--

INSERT INTO `board` (`idBoard`, `idProject`, `dateCreated`, `dateModified`) VALUES
(1, 1, '2025-06-06 11:15:23', '2025-06-06 11:15:23'),
(2, 2, '2025-06-08 09:28:35', '2025-06-08 09:28:35');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `checklist`
--

CREATE TABLE `checklist` (
  `idCheckList` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `checklist_item`
--

CREATE TABLE `checklist_item` (
  `idCLItem` int(11) NOT NULL,
  `idCheckList` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT 0 COMMENT '0: false\r\n1: true'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `idComment` int(11) NOT NULL,
  `idTask` int(11) DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  `content` text DEFAULT NULL,
  `dateCreated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='idItem references Task (not just any Item)';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item`
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

--
-- Đang đổ dữ liệu cho bảng `item`
--

INSERT INTO `item` (`idItem`, `idBacklog`, `backlogType`, `title`, `description`, `dateCreated`, `dateModified`, `status`) VALUES
(2, 1, 1, 'tinh chỉnh khung nhìn', 'tinh chỉnh lại dự án cho phù hợp hơn với mọi người', '2025-06-09 17:06:10', '2025-06-09 17:06:10', 0),
(7, 1, 1, 'Rx2 new', 'des about rx2', '2025-06-09 10:49:11', '2025-06-09 00:00:00', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `label`
--

CREATE TABLE `label` (
  `idLabel` int(11) NOT NULL,
  `idBoard` int(11) NOT NULL,
  `labelName` varchar(255) DEFAULT NULL,
  `color` varchar(255) NOT NULL DEFAULT '#FFFFFF'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `meeting`
--

CREATE TABLE `meeting` (
  `idMeeting` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `meeting_user`
--

CREATE TABLE `meeting_user` (
  `idMeeting` int(11) NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `idNotification` int(11) NOT NULL,
  `idSender` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification_receiver`
--

CREATE TABLE `notification_receiver` (
  `idNotification` int(11) NOT NULL,
  `idReceiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_backlog`
--

CREATE TABLE `product_backlog` (
  `idProductBacklog` int(11) NOT NULL,
  `idProject` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_backlog`
--

INSERT INTO `product_backlog` (`idProductBacklog`, `idProject`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `project`
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
-- Đang đổ dữ liệu cho bảng `project`
--

INSERT INTO `project` (`idProject`, `projectName`, `description`, `dateCreated`, `dateModified`, `status`, `isDisabled`) VALUES
(1, 'Rx1', 'dự án pbl3 của nhóm TDPN', '2025-06-06 11:15:23', '2025-06-06 00:00:00', 0, 0),
(2, 'Rx0', 'dự án pbl2', '2025-06-08 09:28:35', '2025-06-08 00:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sprint`
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

--
-- Đang đổ dữ liệu cho bảng `sprint`
--

INSERT INTO `sprint` (`idSprint`, `idProject`, `title`, `dateStart`, `estimatedEndDate`, `actualEndDate`, `status`) VALUES
(1, 1, 'projectile', '2025-06-18 08:56:34', '2025-06-05', '2025-06-12', '1'),
(2, 1, 'Rx1', '2025-06-09 00:00:00', '2025-06-23', '2025-06-09', '1'),
(3, 1, 'nam cham', '2025-06-09 00:00:00', '2025-06-23', '2025-06-09', '1'),
(4, 1, 'Rx2', '2025-06-09 00:00:00', '2025-06-23', '2025-06-09', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task`
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
-- Đang đổ dữ liệu cho bảng `task`
--

INSERT INTO `task` (`idTask`, `idTaskList`, `idLabel`, `title`, `description`, `dateCreated`, `dateModified`, `dateDue`, `status`, `position`) VALUES
(8, 3, NULL, 'Task 2', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', '2025-06-10 16:25:31', 0, 1),
(9, 3, NULL, 'Task 3', '', '2025-06-06 11:15:23', '2025-06-06 11:15:23', '2025-06-16 16:25:40', 0, 2),
(12, 2, NULL, 'task5', ' mô tả về task05', '2025-06-08 08:23:51', '2025-06-08 08:23:51', NULL, 1, 0),
(20, 4, NULL, 'task09', 'phat dz', '2025-06-08 14:22:09', '2025-06-09 22:12:11', '2025-06-03 00:00:00', 0, 0),
(22, 2, NULL, 'Task 1', '', '2025-06-08 14:22:50', '2025-06-08 14:22:50', NULL, 0, 0),
(24, 5, NULL, 'New Task', 'phat', '2025-06-09 10:36:52', '2025-06-09 10:36:52', NULL, 0, 0),
(28, 5, NULL, 'New Task', ' ', '2025-06-09 10:36:55', '2025-06-09 10:36:55', NULL, 1, 0),
(29, 5, NULL, 'Task 3', 'huhu sao ko luu', '2025-06-09 10:44:01', '2025-06-09 10:44:01', NULL, 0, 2),
(30, 4, NULL, 'New Task', ' ', '2025-06-09 10:44:03', '2025-06-09 22:11:54', '2025-06-09 00:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task_label`
--

CREATE TABLE `task_label` (
  `idTask` int(11) DEFAULT NULL,
  `idLabel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task_list`
--

CREATE TABLE `task_list` (
  `idTaskList` int(11) NOT NULL,
  `idBoard` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `dateCreated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `task_list`
--

INSERT INTO `task_list` (`idTaskList`, `idBoard`, `title`, `position`, `dateCreated`) VALUES
(2, 1, 'In Progress', 1, '2025-06-06 11:15:23'),
(3, 1, 'Done', 2, '2025-06-06 11:15:23'),
(4, 1, 'do', 0, '2025-06-07 14:55:56'),
(5, 1, 'New Task List', 0, '2025-06-08 08:24:04');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
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
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `email`, `fullName`, `role`, `birthday`, `englishName`, `address`, `phone`, `avatar`, `isDisabled`) VALUES
(1, 'System', '', '', '', 0, '0000-00-00', '', '', '', '', 0),
(2, 'admin', 'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', '', '', 1, '2025-06-10', '', '', '', '', 0),
(3, 'testuser', '123456789', 'testemail@gmail.com', 'Test User', 2, '0000-00-00', 'testuser', '1 testuser St.', '0000111222', '', 0),
(9, 'testingagain', 'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', 'phat@gmail.com', 'tran nguyen van phat', 2, '2025-06-04', 'grayzy', '001 ht St', '03568322222', 'src/main/resources/image/ImageAvatar.png', 0),
(10, 'testingagain', 'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', NULL, NULL, 2, '2025-06-04', NULL, NULL, NULL, 'src/main/resources/image/ImageAvatar.png', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_project`
--

CREATE TABLE `user_project` (
  `idUserProject` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idProject` int(11) DEFAULT NULL,
  `relationship` tinyint(1) DEFAULT 0 COMMENT '0: creator\r\n1: member'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Composite PK (idUser, idProject)';

--
-- Đang đổ dữ liệu cho bảng `user_project`
--

INSERT INTO `user_project` (`idUserProject`, `idUser`, `idProject`, `relationship`) VALUES
(9, 9, 1, 0),
(10, 9, 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_task`
--

CREATE TABLE `user_task` (
  `idUserTask` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idTask` int(11) DEFAULT NULL,
  `relationship` tinyint(1) DEFAULT 1 COMMENT '0: assignor\r\n1: assignee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Composite PK (idUser, idTask)';

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `activity_log`
--
ALTER TABLE `activity_log`
  ADD PRIMARY KEY (`idLog`),
  ADD KEY `idBoard` (`idBoard`),
  ADD KEY `idUser` (`idUser`);

--
-- Chỉ mục cho bảng `attachment`
--
ALTER TABLE `attachment`
  ADD PRIMARY KEY (`idAttachment`),
  ADD KEY `attachment_ibfk_1` (`idTask`);

--
-- Chỉ mục cho bảng `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`idBoard`),
  ADD KEY `idProject` (`idProject`);

--
-- Chỉ mục cho bảng `checklist`
--
ALTER TABLE `checklist`
  ADD PRIMARY KEY (`idCheckList`),
  ADD KEY `idTask` (`idTask`);

--
-- Chỉ mục cho bảng `checklist_item`
--
ALTER TABLE `checklist_item`
  ADD PRIMARY KEY (`idCLItem`),
  ADD KEY `idCheckList` (`idCheckList`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`idComment`),
  ADD KEY `comment_ibfk_1` (`idTask`),
  ADD KEY `comment_ibfk_2` (`idUser`);

--
-- Chỉ mục cho bảng `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`),
  ADD KEY `idBacklog` (`idBacklog`);

--
-- Chỉ mục cho bảng `label`
--
ALTER TABLE `label`
  ADD PRIMARY KEY (`idLabel`),
  ADD KEY `label_ibfk_1` (`idBoard`);

--
-- Chỉ mục cho bảng `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`idMeeting`);

--
-- Chỉ mục cho bảng `meeting_user`
--
ALTER TABLE `meeting_user`
  ADD KEY `meeting_user_ibfk_1` (`idMeeting`),
  ADD KEY `meeting_user_ibfk_2` (`idUser`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`idNotification`),
  ADD KEY `idSender` (`idSender`);

--
-- Chỉ mục cho bảng `notification_receiver`
--
ALTER TABLE `notification_receiver`
  ADD KEY `notification_receiver_ibfk_1` (`idNotification`),
  ADD KEY `notification_receiver_ibfk_2` (`idReceiver`);

--
-- Chỉ mục cho bảng `product_backlog`
--
ALTER TABLE `product_backlog`
  ADD PRIMARY KEY (`idProductBacklog`),
  ADD KEY `idProject` (`idProject`);

--
-- Chỉ mục cho bảng `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`idProject`);

--
-- Chỉ mục cho bảng `sprint`
--
ALTER TABLE `sprint`
  ADD PRIMARY KEY (`idSprint`),
  ADD KEY `idProject` (`idProject`);

--
-- Chỉ mục cho bảng `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`idTask`),
  ADD KEY `task_ibfk_2` (`idTask`),
  ADD KEY `idList` (`idTaskList`),
  ADD KEY `task_ibfk_3` (`idLabel`);

--
-- Chỉ mục cho bảng `task_label`
--
ALTER TABLE `task_label`
  ADD KEY `tasklabel_ibfk_1` (`idLabel`),
  ADD KEY `tasklabel_ibfk_2` (`idTask`);

--
-- Chỉ mục cho bảng `task_list`
--
ALTER TABLE `task_list`
  ADD PRIMARY KEY (`idTaskList`),
  ADD KEY `idBoard` (`idBoard`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- Chỉ mục cho bảng `user_project`
--
ALTER TABLE `user_project`
  ADD PRIMARY KEY (`idUserProject`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idProject` (`idProject`);

--
-- Chỉ mục cho bảng `user_task`
--
ALTER TABLE `user_task`
  ADD PRIMARY KEY (`idUserTask`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `usertask_ibfk_2` (`idTask`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `activity_log`
--
ALTER TABLE `activity_log`
  MODIFY `idLog` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `board`
--
ALTER TABLE `board`
  MODIFY `idBoard` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `checklist`
--
ALTER TABLE `checklist`
  MODIFY `idCheckList` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `checklist_item`
--
ALTER TABLE `checklist_item`
  MODIFY `idCLItem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `idComment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `item`
--
ALTER TABLE `item`
  MODIFY `idItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `label`
--
ALTER TABLE `label`
  MODIFY `idLabel` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `meeting`
--
ALTER TABLE `meeting`
  MODIFY `idMeeting` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `idNotification` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `project`
--
ALTER TABLE `project`
  MODIFY `idProject` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `sprint`
--
ALTER TABLE `sprint`
  MODIFY `idSprint` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `task`
--
ALTER TABLE `task`
  MODIFY `idTask` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `task_list`
--
ALTER TABLE `task_list`
  MODIFY `idTaskList` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `user_project`
--
ALTER TABLE `user_project`
  MODIFY `idUserProject` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `user_task`
--
ALTER TABLE `user_task`
  MODIFY `idUserTask` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `activity_log`
--
ALTER TABLE `activity_log`
  ADD CONSTRAINT `activity_log_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`),
  ADD CONSTRAINT `activity_log_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Các ràng buộc cho bảng `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Các ràng buộc cho bảng `board`
--
ALTER TABLE `board`
  ADD CONSTRAINT `board_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `checklist`
--
ALTER TABLE `checklist`
  ADD CONSTRAINT `checklist_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Các ràng buộc cho bảng `checklist_item`
--
ALTER TABLE `checklist_item`
  ADD CONSTRAINT `checklist_item_ibfk_1` FOREIGN KEY (`idCheckList`) REFERENCES `checklist` (`idCheckList`);

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Các ràng buộc cho bảng `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`idBacklog`) REFERENCES `product_backlog` (`idProductBacklog`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`idBacklog`) REFERENCES `sprint` (`idSprint`);

--
-- Các ràng buộc cho bảng `label`
--
ALTER TABLE `label`
  ADD CONSTRAINT `label_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`);

--
-- Các ràng buộc cho bảng `meeting_user`
--
ALTER TABLE `meeting_user`
  ADD CONSTRAINT `meeting_user_ibfk_1` FOREIGN KEY (`idMeeting`) REFERENCES `meeting` (`idMeeting`),
  ADD CONSTRAINT `meeting_user_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Các ràng buộc cho bảng `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`idSender`) REFERENCES `user` (`idUser`);

--
-- Các ràng buộc cho bảng `notification_receiver`
--
ALTER TABLE `notification_receiver`
  ADD CONSTRAINT `notification_receiver_ibfk_1` FOREIGN KEY (`idNotification`) REFERENCES `notification` (`idNotification`),
  ADD CONSTRAINT `notification_receiver_ibfk_2` FOREIGN KEY (`idReceiver`) REFERENCES `user` (`idUser`);

--
-- Các ràng buộc cho bảng `product_backlog`
--
ALTER TABLE `product_backlog`
  ADD CONSTRAINT `product_backlog_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Các ràng buộc cho bảng `sprint`
--
ALTER TABLE `sprint`
  ADD CONSTRAINT `sprint_ibfk_1` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Các ràng buộc cho bảng `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`idTaskList`) REFERENCES `task_list` (`idTaskList`),
  ADD CONSTRAINT `task_ibfk_3` FOREIGN KEY (`idLabel`) REFERENCES `label` (`idLabel`);

--
-- Các ràng buộc cho bảng `task_label`
--
ALTER TABLE `task_label`
  ADD CONSTRAINT `task_label_ibfk_1` FOREIGN KEY (`idLabel`) REFERENCES `label` (`idLabel`),
  ADD CONSTRAINT `task_label_ibfk_2` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);

--
-- Các ràng buộc cho bảng `task_list`
--
ALTER TABLE `task_list`
  ADD CONSTRAINT `task_list_ibfk_1` FOREIGN KEY (`idBoard`) REFERENCES `board` (`idBoard`);

--
-- Các ràng buộc cho bảng `user_project`
--
ALTER TABLE `user_project`
  ADD CONSTRAINT `user_project_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `user_project_ibfk_2` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`);

--
-- Các ràng buộc cho bảng `user_task`
--
ALTER TABLE `user_task`
  ADD CONSTRAINT `user_task_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `user_task_ibfk_2` FOREIGN KEY (`idTask`) REFERENCES `task` (`idTask`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
