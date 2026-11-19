inherit core-image
CORE_IMAGE_EXTRA_INSTALL += "openssh aesd-assignments aesdchar kernel-modules"
inherit extrausers
# We set a default password of root to match our busybox instance setup
# Don't do this in a production image
PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"
# Allow root user to login to the image
EXTRA_IMAGE_FEATURES:append = " allow-root-login"
# Build an ext4 image for ease of use with runqemu
IMAGE_FSTYPES:append = " ext4"
