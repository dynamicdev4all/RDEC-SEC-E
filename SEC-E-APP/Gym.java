public class Gym {
    private String _memberName;
    private String _memberShipType;

    String get_memberName() {
        return _memberName;
    }

    void set_memberName(String memberName) {
        _memberName = memberName;
    }

    String get_memberShip() {
        return _memberShipType;
    }

    void set_memberShip(String memberShip) {
        _memberShipType = memberShip;
    }

    // public String toString() {
    //     return "RDEC";
    // }

    Gym(String memberName, String memberShipType) {
        set_memberName(memberName);
        set_memberShip(memberShipType);
    }
}
