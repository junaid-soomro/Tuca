-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2018 at 09:49 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tuca`
--

-- --------------------------------------------------------

--
-- Table structure for table `cbc`
--

CREATE TABLE `cbc` (
  `cbcId` int(11) NOT NULL,
  `doctorId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `wbcCount` double NOT NULL,
  `rbcCount` double NOT NULL,
  `plt` double NOT NULL,
  `mono` double NOT NULL,
  `lym` double NOT NULL,
  `other` varchar(255) NOT NULL,
  `labId` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cbc`
--

INSERT INTO `cbc` (`cbcId`, `doctorId`, `userId`, `status`, `wbcCount`, `rbcCount`, `plt`, `mono`, `lym`, `other`, `labId`, `time`) VALUES
(1, 11, 1, 1, 3, 7, 7, 7, 77, '777', 1, '2018-03-19 06:12:19'),
(2, 1, 1, 1, 98898, 98, 98, 9, 89, '89898', 2, '2018-03-17 12:15:59'),
(3, 1, 1, 1, 5, 3, 2, 3, 80, 'Mm', 2, '2018-03-17 21:43:23'),
(4, 1, 1, 1, 1.1, 1.1, 1.1, 1.1, 1.1, 'ajsais', 1, '2018-03-24 19:21:31'),
(5, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(6, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(7, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(8, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(9, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(10, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(11, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(12, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(13, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(14, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(15, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(16, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(17, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(18, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(19, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(20, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(21, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(22, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(23, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(24, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(25, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(26, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(27, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(28, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(29, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(30, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(31, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(32, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(33, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(34, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(35, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(36, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(37, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(38, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(39, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(40, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(41, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(42, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(43, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(44, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(45, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(46, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(47, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(48, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(49, 1, 1, 0, 4, 4, 3, 2, 3, '4', 1, '2018-03-17 12:00:14'),
(50, 11, 1, 0, 0, 0, 0, 0, 0, '', 0, '2018-03-19 06:32:49'),
(51, 11, 2, 1, 98, 98, 9, 89, 8, 'asiausuas', 2, '2018-03-19 06:35:20'),
(52, 11, 1, 0, 0, 0, 0, 0, 0, '', 0, '2018-03-19 08:55:20');

-- --------------------------------------------------------

--
-- Table structure for table `clinic`
--

CREATE TABLE `clinic` (
  `clinicId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clinic`
--

INSERT INTO `clinic` (`clinicId`, `name`, `isDelete`) VALUES
(1, 'Cardiology', 1),
(2, 'Teeth', 1),
(3, 'Headache', 1),
(4, 'bbbbbbb', 1),
(5, 'bbbbbbb', 1),
(6, 'heart', 1),
(7, 'sugery', 1),
(8, 'theet', 1),
(9, 'sgery', 1),
(10, 'ssss', 0);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `clinicId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctorId`, `name`, `phone`, `email`, `password`, `clinicId`, `isDelete`, `username`) VALUES
(1, 'fatimah', '01312121212', 'fatimah-alibrahimi@hotmail.com', 'iunc@123', 1, 1, ''),
(2, 'Dr.Majd', '4544555', 'majd@majd.majd', 'ahsaushu', 2, 1, ''),
(3, 'Abdul Majddeed', '19291029', 'abc@yahiio.cin', 'doctor@123', 1, 1, ''),
(4, 'Abdul Majddeed', '19291029', 'abc@yahiio.cina', 'doctor@123', 1, 1, ''),
(5, 'Hello', '1818181800', 'Abc@yahoo.com', '', 2, 0, ''),
(6, 'fatimah', '0956696666', 'fatimah-alibrahimi@hotmail.com', '', 1, 0, ''),
(7, 'aseel', '054327560', 'a@hotmail.com', '', 1, 1, ''),
(8, 'aseel', '0999999999', 'aseel@hotmail.com', '', 3, 1, ''),
(9, 'Aseel', '+966 56 423 7560 ', 'aseel-1415h@hotmail.com', '', 1, 1, 'abcd44'),
(10, 'fatimah', '0999999999', 'fatimah-alibrahimi@hotmail.com', '', 3, 0, ''),
(11, 'Testwww', '01291029', 'testwww@yahoo.com', 'wwwwww', 1, 0, 'abcd1234'),
(12, 'kajksjaks', '929389', 'majd@majd.majd', 'aisaaisj', 1, 0, 'iasiasji');

-- --------------------------------------------------------

--
-- Table structure for table `lab`
--

CREATE TABLE `lab` (
  `labId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lab`
--

INSERT INTO `lab` (`labId`, `name`, `phone`, `email`, `username`, `password`, `isDelete`) VALUES
(1, 'Ali Nawab2', '03192192a', 'amk@yaho.cm', 'xxxxxxxxa', 'aaaaaaaaa', 0),
(2, 'fatimah', '03192192', 'lab@tuca.com', 'abcd', 'tuca@123', 0),
(3, 'abdul moeed', '19281928', 'abc@yahoo.con', 'ahsjas9821', '918291wjwkj', 1),
(4, 'aksjaksj', '10219', 'abc@yahoo.con', 'ajsiasj', 'aksasj', 0);

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `medicineId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `isDelete` int(11) NOT NULL,
  `medicine_category_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`medicineId`, `name`, `isDelete`, `medicine_category_id`, `quantity`) VALUES
(1, 'panadol', 0, 2, 74),
(2, 'Disprene', 0, 2, 90),
(3, 'Tarsiva', 0, 2, 17),
(4, 'Marzene', 0, 2, 98),
(5, 'panadol2', 1, 0, 0),
(6, 'Tablet', 0, 2, 100),
(7, 'Capsole', 1, 0, 0),
(8, 'Marzane', 0, 3, 15);

-- --------------------------------------------------------

--
-- Table structure for table `medicine_category`
--

CREATE TABLE `medicine_category` (
  `medicine_category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine_category`
--

INSERT INTO `medicine_category` (`medicine_category_id`, `name`, `isDelete`) VALUES
(1, 'Tablet', 1),
(2, 'Capsole', 0),
(3, 'Tablet', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist`
--

CREATE TABLE `pharmacist` (
  `pharmacistId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacist`
--

INSERT INTO `pharmacist` (`pharmacistId`, `name`, `email`, `username`, `password`, `phone`, `isDelete`) VALUES
(1, 'moeed', 'b@b.bodf', 'bbbfdf', 'opop', '121212', 1),
(2, 'moeed', 'b@b.bo', 'bbb', 'opop', '121212', 1),
(3, 'nanna', 'abc@yahoo.com', 'aksj19', 'dkjskdsjd', '989988', 0);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `prescriptionId` int(11) NOT NULL,
  `doctorId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `isDone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescriptionId`, `doctorId`, `userId`, `isDone`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 1),
(3, 1, 1, 0),
(4, 1, 2, 1),
(5, 1, 2, 1),
(6, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `prescription_medicine`
--

CREATE TABLE `prescription_medicine` (
  `prescriptionId` int(11) NOT NULL,
  `medicineId` int(11) NOT NULL,
  `prescription_medicine_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription_medicine`
--

INSERT INTO `prescription_medicine` (`prescriptionId`, `medicineId`, `prescription_medicine_id`) VALUES
(1, 3, 1),
(1, 4, 2),
(2, 3, 3),
(2, 4, 4),
(3, 1, 5),
(3, 2, 6),
(4, 4, 7),
(5, 2, 8),
(5, 3, 9),
(6, 1, 10),
(6, 2, 11),
(6, 3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `name`, `email`, `gender`) VALUES
(1, 'Monis', 'ahm@ja.a', 'MALE'),
(2, 'Moeed', 'ahm@ja.a', 'MALE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cbc`
--
ALTER TABLE `cbc`
  ADD PRIMARY KEY (`cbcId`);

--
-- Indexes for table `clinic`
--
ALTER TABLE `clinic`
  ADD PRIMARY KEY (`clinicId`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorId`);

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`labId`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`medicineId`);

--
-- Indexes for table `medicine_category`
--
ALTER TABLE `medicine_category`
  ADD PRIMARY KEY (`medicine_category_id`);

--
-- Indexes for table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`pharmacistId`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`prescriptionId`);

--
-- Indexes for table `prescription_medicine`
--
ALTER TABLE `prescription_medicine`
  ADD PRIMARY KEY (`prescription_medicine_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cbc`
--
ALTER TABLE `cbc`
  MODIFY `cbcId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `clinic`
--
ALTER TABLE `clinic`
  MODIFY `clinicId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `lab`
--
ALTER TABLE `lab`
  MODIFY `labId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `medicineId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `medicine_category`
--
ALTER TABLE `medicine_category`
  MODIFY `medicine_category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pharmacist`
--
ALTER TABLE `pharmacist`
  MODIFY `pharmacistId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `prescriptionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `prescription_medicine`
--
ALTER TABLE `prescription_medicine`
  MODIFY `prescription_medicine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
