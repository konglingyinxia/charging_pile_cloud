// UART1上行通讯字符串：桩号(4BYTE)，桩状态(1BYTE)，授权状态(1BYTE)，电表度数(4BYTE),是否刷卡(1BYTE)，卡号(7BYTE)
// 上位机下行回应时，可以不带卡号，如：桩号(4BYTE)，桩状态(1BYTE)，授权状态(1BYTE)  即可，后面字段对硬件端无意义。


// 以下为单片机处理程序，供参考
#if (BSP_UART1_FUNC == ENABLE)		// UART1 DTU
UART_RING_BUFFER_T rb1;
uint32_t  uart1_len;
// UART1通讯字符串：桩号(4BYTE)，桩状态(1BYTE)，授权状态(1BYTE)，电表度数(4BYTE),是否刷卡(1BYTE)，卡号(7BYTE)
uint8_t uart1_buf[24];		// UART1缓冲区，定长
UART1_COMM_Type *uart1_comm_type;							// UART1通讯结构体指针变量，可以解析UART1缓冲区数组为结构体元素，夏秋航
#endif



#if (BSP_UART1_FUNC == ENABLE)	// DTU
		if(rb1.rx_int)	// 如果接收到平台数据
		{
			uart1_len = UARTReceive(XIA_LPC_UART1, uart1_buf, sizeof(uart1_buf));	// 读出到uart1_buf
			
			if(HexAscii_Int32(uart1_comm_type->pile_id) == PILE_ID)					// 如果地址匹配
			{
				AUTH_STATUS=uart1_comm_type->auth_status - '0';				// 更新网络授权状态
			}
			__BUF_RESET(rb1.rx_int);												// 清空接收状态
		}
		// 如果发送定时到，则发送一次数据
		if (TMR_POST_MSG==SET)
		{
			TMR_POST_MSG=RESET;
			uart1_comm_type->pile_id = Int32_HexAscii(PILE_ID);
			uart1_comm_type->comma1 = ',';
			
			uart1_comm_type->pile_status = PILE_STATUS+'0';
			uart1_comm_type->comma2 = ',';
			
			uart1_comm_type->auth_status = AUTH_STATUS+'0';
			uart1_comm_type->comma3 = ',';
			
			uart1_comm_type->power_meter = Int32_HexAscii(POWER_METER_NOW);			// 当前消耗电量
			uart1_comm_type->comma4 = ',';
			
			uart1_comm_type->card_flag = CARD_FLAG+'0';								// 刷卡标志
			uart1_comm_type->comma5 = ',';
			
			if(CARD_FLAG==SET)
			{
				for(i=0;i<7;i++)
				{	// 复制UID卡号
					uart1_comm_type->card_id[i]=uart3_comm_type->card_id[i]+'0';			// 这里是UID卡号，上位机需转十六进制字符，也可转十进制
				}
			}
			else
			{
				for(i=0;i<7;i++)
				{
					uart1_comm_type->card_id[i]='0';
				}
			}
			uart1_comm_type->str_end = 0x00;
			
			uart1_len = UARTSend(XIA_LPC_UART1, uart1_buf, sizeof(uart1_buf));		// 发送出去
		}
#endif




// 把数字换算成十六进制ASCII序列
uint32_t Int32_HexAscii(uint32_t num)	
{
	uint32_t hex_ascii=0,mask=0xff,tmp=0;
	
	tmp=num>>8&mask + '0';
	hex_ascii |= tmp;
	
	tmp=num>>8&mask + '0';
	tmp=tmp<<8;
	hex_ascii |= tmp;
	
	tmp=num>>8&mask + '0';
	tmp=tmp<<16;
	hex_ascii |= tmp;

	tmp=num&mask + '0';
	tmp=tmp<<24;
	hex_ascii |= tmp;
	
	return hex_ascii;
}
// 把十六进制ASCII序列还原成数字
uint32_t HexAscii_Int32(uint32_t hex_ascii)	
{
	uint32_t num=0,mask=0xff,tmp=0;
	
	tmp=hex_ascii>>8&mask - '0';
	num |= tmp;
	
	tmp=hex_ascii>>8&mask - '0';
	tmp=tmp<<8;
	num |= tmp;
	
	tmp=hex_ascii>>8&mask - '0';
	tmp=tmp<<16;
	num |= tmp;

	tmp=num&mask - '0';
	tmp=tmp<<24;
	hex_ascii |= tmp;
	
	return num;
}