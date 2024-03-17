echo "SocketLimitsChatter Histogram"
jcmd SocketLimitsChatter GC.class_histogram | head -30
jcmd SocketLimitsChatter GC.class_histogram | tail -1

echo " "
echo "ServerChatter Histogram"
jcmd ServerChatter GC.class_histogram | head -30
jcmd ServerChatter GC.class_histogram | tail -1